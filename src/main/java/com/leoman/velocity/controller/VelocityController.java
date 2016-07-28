package com.leoman.velocity.controller;

import com.leoman.common.log.entity.LogEntity;
import com.leoman.common.log.service.LogService;
import com.leoman.common.log.service.impl.LogServiceImpl;
import com.leoman.image.entity.FileBo;
import com.leoman.utils.BeanUtil;
import com.leoman.utils.FileUtil;
import com.leoman.utils.JsonUtil;
import com.leoman.utils.Result;
import com.leoman.velocity.analysis.CURDCoreAnalysis;
import com.leoman.velocity.analysis.CURDFileCreateAnalysis;
import com.leoman.velocity.core.CodeGenerator;
import com.leoman.velocity.entity.DD;
import com.leoman.velocity.entity.DDSub;
import com.leoman.velocity.entity.TableEntity;
import com.leoman.velocity.file.NewFile;
import com.leoman.velocity.model.*;
import com.leoman.velocity.model.ui.*;
import com.leoman.velocity.service.DDService;
import com.leoman.velocity.service.DDSubService;
import com.leoman.velocity.util.ModelSortCompare;
import com.qiniu.util.Json;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by Administrator on 2016/6/22.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/admin/velocity")
public class VelocityController {

    @Autowired
    private DDService ddService;

    @Autowired
    private DDSubService ddSubService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {

//        LogServiceImpl logService = (LogServiceImpl) BeanUtil.getBean("logService");
//        LogEntity logEntity = logService.queryByPK(4L);
        model.addAttribute("list",ddService.findAllValidateRule());
//        model.addAttribute("log11",logEntity);
        return "velocity/list";
    }

    @RequestMapping(value = "/ddIndex", method = RequestMethod.GET)
    public String ddIndex(Model model) {

        List<DD> list = ddService.queryAll();
        model.addAttribute("list", list);
        if (list != null && !list.isEmpty()) {
            DD dd = list.get(0);
            List<DDSub> subList = ddSubService.queryByProperty("ddId", dd.getId());
            model.addAttribute("sublist", subList);
            model.addAttribute("ddId", dd.getId());
        }
        return "velocity/dd-list";
    }


    @RequestMapping(value = "/createDD", method = RequestMethod.POST)
    @ResponseBody
    public Result createDD(DD dd) {
        ddService.save(dd);
        return Result.success();
    }

    /**
     * 获取数据字典名字列表
     *
     * @return
     */
    @RequestMapping(value = "/ddList", method = RequestMethod.POST)
    @ResponseBody
    public Result ddList() {
        List<DD> list = ddService.queryAll();
        return Result.success(list);
    }

    /**
     * 获取数据字典名字列表
     *
     * @return
     */
    @RequestMapping(value = "/defaultText", method = RequestMethod.POST)
    @ResponseBody
    public Result defaultText() {
        List<ValidateRule> list =  ddService.findAllValidateRule();
        List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();

        for(ValidateRule validateRule : list) {
            Map<String,String> map = new HashMap<String,String>();
            map.put("ruleName",validateRule.getRuleName());
            map.put("regex",validateRule.getRegex());
            mapList.add(map);
        }
        return Result.success(mapList);
    }

    /**
     * 删除数据字典
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/ddDelete", method = RequestMethod.POST)
    @ResponseBody
    public Result ddDelete(Long id) {
        ddService.delete(ddService.queryByPK(id));

        List<DDSub> list = ddSubService.queryByProperty("ddId", id);
        Long[] ids = new Long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ids[i] = list.get(i).getId();
        }
        ddSubService.deleteByPKs(ids);
        return Result.success();
    }

    /**
     * 新增数据字典key_value
     *
     * @param ddSub
     * @return
     */
    @RequestMapping(value = "/createDDSub", method = RequestMethod.POST)
    @ResponseBody
    public Result createDDSub(DDSub ddSub) {
        ddSubService.save(ddSub);
        return Result.success();
    }

    /**
     * 根据数据字典ID获取数据字段key_value值
     *
     * @param ddId
     * @return
     */
    @RequestMapping(value = "/ddSubList", method = RequestMethod.POST)
    @ResponseBody
    public Result ddSubList(Long ddId) {
        List<DDSub> list = ddSubService.queryByProperty("ddId", ddId);
        return Result.success(list);
    }

    /**
     * 删除数据字段KEY_VALUE值
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/ddSubDelete", method = RequestMethod.POST)
    @ResponseBody
    public Result ddSubDelete(Long id) {
        ddSubService.delete(ddSubService.queryByPK(id));
        return Result.success();
    }


    @RequestMapping(value = "/analysis", method = RequestMethod.POST)
    public String analysis(@RequestParam(value = "file", required = true) MultipartFile file, Model _model,HttpServletRequest request) {

        try {
            FileBo fileBo = FileUtil.save(file, file.getOriginalFilename());
            String absPath = fileBo.getFile().getAbsolutePath();

            BufferedReader reader = new BufferedReader(new FileReader(fileBo.getFile()));
            String pacakge = reader.readLine();

            String pacakgeName = pacakge.split(" ")[1];
            CURDCoreAnalysis analysis = CURDCoreAnalysis.getInstance();
            String filename = fileBo.getFile().getName().split("\\.")[0];
            String srcJava = pacakgeName.substring(0, pacakgeName.length() - 1) + "." + filename;
            EntityModel model = analysis.analysis(srcJava);
            _model.addAttribute("fields", model.getFields());
            _model.addAttribute("srcJava",srcJava);
            _model.addAttribute("list",ddService.findAllValidateRule());

            request.getSession().setAttribute("absPath",absPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "velocity/list";
    }

    /**
     * 生成代码
     *
     * @param tabString
     * @return
     */
    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    @ResponseBody
    public Result generate(String tabString,String srcJava,HttpServletRequest request) {

        List<String> list = JsonUtil.json2Obj(tabString, List.class);
        // list : [{"c1":"id","c2":"","c3":"Default Text","c4":"手机","c5":false},{"c1":"name","c2":"","c3":"Default Text","c4":"手机","c5":false},{"c1":"desc","c2":"","c3":"Default Text","c4":"手机","c5":false}]
        // add : [{"c1":"id","c3":"Default Text","c4":"手机","c5":false},{"c1":"name","c3":"Default Text","c4":"手机","c5":false},{"c1":"desc","c3":"Default Text","c4":"手机","c5":false}]
        List<TableEntity> listList = JsonUtil.json2List(list.get(0),TableEntity[].class);
        List<TableEntity> addList  = JsonUtil.json2List(list.get(1),TableEntity[].class);
        EntityModel entityModel =  CURDCoreAnalysis.getInstance().analysis(srcJava);

        EntityViewUI ui = new EntityViewUI();
        ui.setEntityModel(entityModel);
        analysisTableEntity(ui,listList,EntityViewUI.TYPE_LIST);
        analysisTableEntity(ui,addList,EntityViewUI.TYPE_ADD);

        String filePath = (String) request.getSession().getAttribute("absPath");
        String projectPath = filePath.substring(0, filePath.indexOf("/src/main/java/"));
        List<NewFile> createFiles = CURDFileCreateAnalysis.getCreateFileList(projectPath, ui);
        try {
            CodeGenerator.generateCode(createFiles, entityModel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.success();
    }


    public void analysisTableEntity(EntityViewUI ui,List<TableEntity> tableEntities,String type) {

        List<UIWidget> uiWidgets = new ArrayList<UIWidget>();
        for (TableEntity tableEntity : tableEntities) {
            UIWidget uiWidget = tableEntity2UIWidget(tableEntity);
            uiWidgets.add(uiWidget);
        }

        if(type.equals(EntityViewUI.TYPE_ADD)) {
            Collections.sort(uiWidgets,new ModelSortCompare());
            AddModel model = new AddModel(uiWidgets);
            ui.setAddModel(model);
        }
        else if(type.equals(EntityViewUI.TYPE_LIST)) {
            ListModel model = new ListModel(uiWidgets);
            ui.setListModel(model);
        }
    }

    /**
     * tableEntity转UIWidget
     * @param tableEntitie
     * @return
     */
    public UIWidget tableEntity2UIWidget(TableEntity tableEntitie) {

        String colunmTypeStr = tableEntitie.getC3();
        String ddId = tableEntitie.getC4();
        UIWidget uiWidget = null;
        switch (colunmTypeStr) {
            case "defaultText" :
                uiWidget = TextUIWidget.createTextUIWidget(tableEntitie);
                break;
            case "image" :
                uiWidget = ImageUIWidget.createImageUIWidget(tableEntitie);
                break;
            case "richText" :
                uiWidget = RichUIWidget.createImageUIWidget(tableEntitie);
                break;
            case "select" :
                List<DDSub> selectList = ddSubService.queryByProperty("ddId", ddId);
                tableEntitie.setList(selectList);
                uiWidget = SelectUIWidget.createSelectUIWidget(tableEntitie);
                break;
            case "radio" :
                List<DDSub> radioList = ddSubService.queryByProperty("ddId", ddId);
                tableEntitie.setList(radioList);
                uiWidget = RadioUIWidget.createRadioUIWidget(tableEntitie);
                break;
            case "checkbox" :
//                List<DDSub> checkboxList = ddSubService.queryByProperty("ddId", ddId);
//                tableEntitie.setList(checkboxList);
//                uiWidget = CheckboxWidget.createCheckboxWidget(tableEntitie);
                break;
        }
        return uiWidget;
    }

    /**
     * 文件下载
     *
     * @return
     */
    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download() throws IOException {
        String path = "E:\\gson.zip";
//        String path="D:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\springMVC\\WEB-INF\\upload\\图片10（定价后）.xlsx";
        File file = new File(path);
        HttpHeaders headers = new HttpHeaders();
        String fileName = new String("gson.zip".getBytes("UTF-8"), "iso-8859-1");//为了解决中文名称乱码问题
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }
}
