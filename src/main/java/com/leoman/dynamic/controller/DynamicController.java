package com.leoman.dynamic.controller;

import com.leoman.admin.entity.Admin;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.core.Constant;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.dynamic.entity.Dynamic;
import com.leoman.dynamic.entity.DynamicImage;
import com.leoman.dynamic.service.DynamicImageService;
import com.leoman.dynamic.service.DynamicService;
import com.leoman.dynamic.service.impl.DynamicServiceImpl;
import com.leoman.image.entity.FileBo;
import com.leoman.user.entity.UserInfoCa;
import com.leoman.utils.FileUtil;
import com.leoman.utils.JsonUtil;
import com.leoman.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/28.
 */
@RequestMapping(value = "/admin/dynamic")
@Controller
public class DynamicController extends GenericEntityController<Dynamic, Dynamic, DynamicServiceImpl> {

    @Autowired
    private DynamicService dynamicService;

    @Autowired
    private DynamicImageService dynamicImageService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "dynamic/list";
    }

    /**
     * 列表查询
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(Dynamic dynamic, Integer start, Integer length, Integer draw) {

        int currentPage = getPageNum(start, length);
        Page<Dynamic> page = dynamicService.findPage(dynamic, currentPage, length);
        return DataTableFactory.fitting(draw, page);
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Long id,Model model) {
        model.addAttribute("dynamic", dynamicService.queryByPK(id));
        model.addAttribute("list",dynamicImageService.queryByProperty("dynamicId",id));
        return "dynamic/detail";
    }


    /**
     * 跳转编辑页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Long id, Model model) {

        if (id != null) {
            model.addAttribute("dynamic", dynamicService.queryByPK(id));
        }
        return "dynamic/add";
    }

    /**
     * 保存
     *
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(HttpServletRequest reuqest, Long id, String content,String imageIds) {

        Dynamic dynamic = null;
        if (id != null) {
            dynamic = dynamicService.queryByPK(id);
        } else {
            Admin admin = (Admin) reuqest.getSession().getAttribute(Constant.SESSION_MEMBER_GLOBLE);
                    dynamic = new Dynamic();
            dynamic.setContent(content);
            dynamic.setCreatorId(admin.getId());
            dynamic.setIsTop(1);
            dynamic.setStatus(0);
        }
        try {
            dynamicService.saveDynamic(dynamic,imageIds);
            // TODO 保存图片

        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

    /**
     * 上传荣誉证书
     * @param request
     * @param files
     * @return
     */
    @RequestMapping(value = "/uploadCa")
    @ResponseBody
    public Result uploadCa(HttpServletRequest request ,@RequestParam(required = false) MultipartFile[] files) {

        DynamicImage ca = null;
        List<DynamicImage> list = new ArrayList<DynamicImage>();
        try {
            for (MultipartFile file : files) {
                FileBo fileBo = FileUtil.save(file);
                ca = new DynamicImage();
                ca.setImageUrl(fileBo.getPath());
                dynamicImageService.save(ca);
                list.add(ca);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success(list);
    }

    /**
     * 显示 / 隐藏
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    @ResponseBody
    public Result status(Long id,Integer status) {

        if(id == null) {return Result.failure();}
        Dynamic dynamic = dynamicService.queryByPK(id);
        try {
            dynamic.setStatus(status);
            dynamicService.save(dynamic);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

    /**
     * 删除图片
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteImage", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteImage(Long id) {
        dynamicImageService.delete(dynamicImageService.queryByPK(id));
        return Result.success();
    }

    /**
     * 删除
     * @param id
     * @param ids
     * @return
     */
    @RequestMapping(value = "/del")
    @ResponseBody
    public Result del(Long id,String ids) {
        if (id == null && StringUtils.isBlank(ids)) {
            return Result.failure();
        }
        try {
            if (id != null) {
                dynamicService.delete(dynamicService.queryByPK(id));
            } else {
                Long[] ss = JsonUtil.json2Obj(ids, Long[].class);
                for (Long _id : ss) {
                    dynamicService.delete(dynamicService.queryByPK(_id));
                }
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }


}
