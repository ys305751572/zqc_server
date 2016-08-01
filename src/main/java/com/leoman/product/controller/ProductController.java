package com.leoman.product.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.product.entity.Product;
import com.leoman.product.entity.ProductAds;
import com.leoman.product.service.ProductAdsService;
import com.leoman.product.service.ProductService;
import com.leoman.product.service.impl.ProductServiceImpl;
import com.leoman.task.entity.TaskJoin;
import com.leoman.team.entity.Team;
import com.leoman.user.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 商城管理-商品列表
 * Created by Administrator on 2016/8/1.
 */
@Controller
@RequestMapping(value = "/admin/product")
public class ProductController extends GenericEntityController<Product,Product,ProductServiceImpl>{

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductAdsService productAdsService;

    @RequestMapping(value = "/index")
    public String index(){
        return "product/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> list(Product product, Integer start, Integer length, Integer draw) {
        int currentPage = getPageNum(start, length);
        Page<Product> page = productService.findPage(product, currentPage, length);
        return DataTableFactory.fitting(draw, page);
    }

    @RequestMapping(value = "/detail")
    public String detail(Long id, Model model){
        Product product = productService.queryByPK(id);
        model.addAttribute("product",product);
        if(product.getType()==1){
            Integer ym = product.getYm()*product.getNums();
            model.addAttribute("ym",ym);
        }
        if(product.getType()==2){
            List<ProductAds> ProductAdsList = productAdsService.queryByProperty("productId",id);
            if(!ProductAdsList.isEmpty() && ProductAdsList.size()>0){
                model.addAttribute("ProductAdsList",ProductAdsList);
            }
        }
        return "product/detail";
    }
}
