package com.leoman.user.controller;

import com.leoman.admin.entity.Admin;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.core.Constant;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.image.entity.FileBo;
import com.leoman.user.entity.UserInfo;
import com.leoman.user.entity.UserInfoCa;
import com.leoman.user.service.UserInfoCaService;
import com.leoman.user.service.UserInfoService;
import com.leoman.user.service.impl.UserInfoServiceImpl;
import com.leoman.utils.ConfigUtil;
import com.leoman.utils.FileUtil;
import com.leoman.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/22.
 */
@RequestMapping("/admin/userinfo")
@Controller
public class UserInfoController extends GenericEntityController<UserInfo, UserInfo, UserInfoServiceImpl> {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserInfoCaService userInfoCaService;
    /**
     * 会员列表首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "userinfo/list";
    }

    /**
     * 类别查询
     *
     * @param userInfo
     * @param draw
     * @param start
     * @param length
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(UserInfo userInfo, Integer draw, Integer start, Integer length) {

        Integer currentPage = getPageNum(start, length);
        Page<UserInfo> page = userInfoService.findPage(userInfo, currentPage, length);
        return DataTableFactory.fitting(draw, page);
    }

    /**
     * 跳转至新增/编辑页面
     *
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Long id, Model model) {

        try {
            if (id != null) {
                UserInfo userInfo = userInfoService.queryByPK(id);
                model.addAttribute("user", userInfo);

                List<UserInfoCa> list = userInfoCaService.queryByProperty("userId",id);
                for (UserInfoCa ca : list) {
                    ca.setCaUrl(StringUtils.isNotBlank(ca.getCaUrl()) ? ConfigUtil.getString("upload.url") + ca.getCaUrl() : "");
                }
                model.addAttribute("list",list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "userinfo/add";
    }

    /**
     * 新增/编辑保存
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(UserInfo userInfo,String imageIds) {
        try {
            userInfoService.saveUserInfo(userInfo,imageIds);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("保存失败");
        }
        return Result.success();
    }

    /**
     * 跳转详细信息页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Long id, Model model) {
        model.addAttribute("user", userInfoService.queryByPK(id));
        List<UserInfoCa> list = userInfoCaService.queryByProperty("userId",id);
        for (UserInfoCa ca : list) {
            ca.setCaUrl(StringUtils.isNotBlank(ca.getCaUrl()) ? ConfigUtil.getString("upload.url") + ca.getCaUrl() : "");
        }
        model.addAttribute("list",list);
        return "userinfo/detail";
    }

    /**
     * 修改用户状态
     * @param status
     * @return
     */
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    @ResponseBody
    public Result modifyStatus(Long id,Integer status) {

        UserInfo userInfo = userInfoService.queryByPK(id);
        if(userInfo == null) return Result.failure();
        userInfo.setStatus(status);

        userInfoService.save(userInfo);
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

        Admin admin = (Admin) request.getSession().getAttribute(Constant.SESSION_MEMBER_GLOBLE);
        UserInfoCa ca = null;
        List<UserInfoCa> list = new ArrayList<UserInfoCa>();
        try {
            for (MultipartFile file : files) {
                FileBo fileBo = FileUtil.save(file);
                ca = new UserInfoCa();
                ca.setCaUrl(fileBo.getPath());
                ca.setCreatorId(admin.getId());
                userInfoCaService.save(ca);

                list.add(ca);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success(list);
    }

    /**
     * 删除荣誉证书
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteCa")
    @ResponseBody
    public Result deleteCa(Long id) {
        userInfoCaService.delete(userInfoCaService.queryByPK(id));
        return Result.success();
    }
}
