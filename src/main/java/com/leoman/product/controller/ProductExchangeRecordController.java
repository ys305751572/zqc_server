package com.leoman.product.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.product.entity.Product;
import com.leoman.product.entity.ProductExchangeRecord;
import com.leoman.product.service.ProductExchangeRecordService;
import com.leoman.product.service.impl.ProductExchangeRecordServiceImpl;
import com.leoman.team.service.TeamService;
import com.leoman.user.service.UserInfoService;
import com.leoman.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 商品兑换列表
 * Created by Administrator on 2016/8/3.
 */
@Controller
@RequestMapping(value = "/admin/productExchangeRecord")
public class ProductExchangeRecordController extends GenericEntityController<ProductExchangeRecord,ProductExchangeRecord,ProductExchangeRecordServiceImpl>{

    @Autowired
    private ProductExchangeRecordService productExchangeRecordService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 页面
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(){
        return "productexchangerecord/list";
    }

    /**
     * 列表
     * @param productExchangeRecord
     * @param start
     * @param length
     * @param draw
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String,Object> list(ProductExchangeRecord productExchangeRecord,Integer start, Integer length, Integer draw){
        int currentPage = getPageNum(start, length);
        Page<ProductExchangeRecord> page = productExchangeRecordService.findPage(productExchangeRecord, currentPage, length);
        return DataTableFactory.fitting(draw, page);
    }

    /**
     * 处理请求
     * @param id
     * @return
     */
    @RequestMapping(value = "/check")
    @ResponseBody
    public Result check(Long id){
        if(id==null){
            return Result.failure();
        }
        try{
            ProductExchangeRecord p = productExchangeRecordService.queryByPK(id);
            p.setStatus(1);
            productExchangeRecordService.update(p);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

}
