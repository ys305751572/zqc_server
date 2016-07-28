package com.leoman.content.controller;

import com.google.gson.JsonArray;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.content.entity.Banner;
import com.leoman.content.service.BannerService;
import com.leoman.content.service.impl.BannerServiecImpl;
import com.leoman.image.entity.FileBo;
import com.leoman.utils.*;
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

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/28.
 */
@Controller
@RequestMapping(value = "/admin/banner")
public class BannerController extends GenericEntityController<Banner, Banner, BannerServiecImpl> {

    @Autowired
    private BannerService bannerService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "banner/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(Banner banner, Integer start, Integer length, Integer draw) {

        int currentPage = getPageNum(start, length);
        Page<Banner> page = bannerService.findPage(banner, currentPage, length);
        List<Banner> list = page.getContent();
        for (Banner banner1 : list) {
            banner1.setImageUrl(StringUtils.isNotBlank(banner1.getImageUrl()) ? ConfigUtil.getString("upload.url") + banner1.getImageUrl() : "");
        }
        return DataTableFactory.fitting(draw, page);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Long id, Model model) {

        if (id != null) {
            model.addAttribute("banner", bannerService.queryByPK(id));
        }
        return "banner/add";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Long id, Model model) {
        if (id != null) {
            model.addAttribute("banner", bannerService.queryByPK(id));
        }
        return "banner/detail";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(Long id) {
        if (id != null) {

            bannerService.delete(bannerService.queryByPK(id));
        } else {
            return Result.failure();
        }
        return Result.success();
    }

    /**
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(Long id, Integer position, String startDate, String endDate1, @RequestParam(value = "imageFile", required = false) MultipartFile file) {

        try {
            Banner banner = new Banner();
            if (file != null) {
                FileBo fileBo = FileUtil.save(file);
                banner.setImageUrl(fileBo.getPath());
            }
            banner.setId(id);
            banner.setPosition(position);
            banner.setStartDate(StringUtils.isNotBlank(startDate) ? DateUtils.stringToLong(startDate, "yyyy-MM-dd") : 0L);
            banner.setEndDate(StringUtils.isNotBlank(endDate1) ? DateUtils.stringToLong(endDate1, "yyyy-MM-dd") : 0L);
            bannerService.save(banner);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/batchDel", method = RequestMethod.POST)
    @ResponseBody
    public Result batchDel(String ids) {

        if (StringUtils.isBlank(ids)) return Result.failure();
        try {
            Long[] ss = JsonUtil.json2Obj(ids,Long[].class);
            for (Long id : ss) {
                bannerService.delete(bannerService.queryByPK(id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success();
    }
}
