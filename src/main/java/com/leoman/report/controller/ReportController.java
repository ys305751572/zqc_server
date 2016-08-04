package com.leoman.report.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.product.entity.Product;
import com.leoman.report.entity.Report;
import com.leoman.report.service.ReportService;
import com.leoman.report.service.impl.ReportServiceImpl;
import com.leoman.user.entity.UserInfo;
import com.leoman.utils.JsonUtil;
import com.leoman.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 站长工具-意见反馈
 * Created by Administrator on 2016/8/3.
 */
@Controller
@RequestMapping(value = "/admin/report")
public class ReportController extends GenericEntityController<Report,Report,ReportServiceImpl>{

    @Autowired
    private ReportService reportService;

    /**
     * 页面
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(){
        return "report/list";
    }

    /**
     * 列表
     * @param report
     * @param start
     * @param length
     * @param draw
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String,Object> list(Report report,Integer start, Integer length, Integer draw,String mobile){
        int currentPage = getPageNum(start, length);
        UserInfo u = new UserInfo();
        u.setMobile(mobile);
        report.setUserInfo(u);
        Page<Report> page = reportService.findPage(report, currentPage, length);
        return DataTableFactory.fitting(draw, page);
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/del")
    @ResponseBody
    public Result del(String ids){
        if(StringUtils.isBlank(ids)){
            return Result.failure();
        }
        try{
            Long[] ss = JsonUtil.json2Obj(ids,Long[].class);
            for(Long _id : ss){
                reportService.delete(reportService.queryByPK(_id));
            }

        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

}
