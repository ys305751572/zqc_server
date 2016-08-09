package com.leoman.product.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.image.entity.FileBo;
import com.leoman.product.entity.Product;
import com.leoman.product.entity.ProductAds;
import com.leoman.product.service.ProductAdsService;
import com.leoman.product.service.ProductService;
import com.leoman.product.service.impl.ProductServiceImpl;
import com.leoman.task.entity.TaskJoin;
import com.leoman.team.entity.Team;
import com.leoman.user.entity.UserInfo;
import com.leoman.utils.FileUtil;
import com.leoman.utils.JsonUtil;
import com.leoman.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    /**
     * 列表
     * @param product
     * @param start
     * @param length
     * @param draw
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> list(Product product, Integer start, Integer length, Integer draw) {
        int currentPage = getPageNum(start, length);
        Page<Product> page = productService.findPage(product, currentPage, length);
        return DataTableFactory.fitting(draw, page);
    }

    /**
     * 详情页
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail")
    public String detail(Long id, Model model){
        this.modelAdd(id,model);
        return "product/detail";
    }

    /**
     * 新增编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "add")
    public String add(Long id,Model model){
        if(id!=null){
            this.modelAdd(id,model);
        }
        return "product/add";
    }

    /**
     * 保存
     * @param product
     * @param imageFile
     * @param imageFile1
     * @param dgym
     * @param detail
     * @param adsDays
     * @param adsYms
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save(Product product, @RequestParam(value = "imageFile",required = false) MultipartFile imageFile,@RequestParam(value = "imageFile1",required = false) MultipartFile imageFile1,Integer dgym, String detail, String adsDays, String adsYms){
        Product p = null;
        try {
            if(null != product.getId()){
                p = productService.queryByPK(product.getId());
            }

            if(null != p){
                product.setType(p.getType());
                product.setCreateDate(p.getCreateDate());
                product.setCoverUrl(p.getCoverUrl());
                product.setDetailImageUrl(p.getDetailImageUrl());
                if(product.getType()==2){
                    product.setYm(p.getYm());
                }
                if(product.getType()!=1){
                    product.setNums(p.getNums());
                }
                product.setBuyNum(p.getBuyNum());
                product.setWishingWell(p.getWishingWell());
            }else {
                if(product.getType()==2){
                    product.setYm(0);
                }
                if(product.getType()!=1){
                    product.setNums(0);
                }
                product.setBuyNum(0);
                product.setWishingWell(0);
            }
            if(imageFile!=null && imageFile.getSize()>0) {
                FileBo fileBo = null;
                try {
                    fileBo = FileUtil.save(imageFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (fileBo != null && StringUtils.isNotBlank(fileBo.getPath())) {
                    product.setCoverUrl(fileBo.getPath());
                }
            }
            if(imageFile1!=null && imageFile1.getSize()>0) {
                FileBo fileBo = null;
                try {
                    fileBo = FileUtil.save(imageFile1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (fileBo != null && StringUtils.isNotBlank(fileBo.getPath())) {
                    product.setDetailImageUrl(fileBo.getPath());
                }
            }
            if (detail != null) {
                product.setDetailDesc(detail.replace("&lt", "<").replace("&gt", ">"));
            }
            productService.save(product);

            if(product.getId()!=null && product.getType()==2){
                List<ProductAds> list = productAdsService.queryByProperty("productId",(product.getId()));
                if(!list.isEmpty() && list.size()>0){
                    for(ProductAds _p : list){
                        productAdsService.delete(_p);
                    }
                }
//                this.adsSave(product.getId(),adsDays,adsYms);
                productAdsService.adsSave(product.getId(),adsDays,adsYms);
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
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
    public Result del(Long id,String ids){
        if(id==null && StringUtils.isBlank(ids)){
            return Result.failure();
        }
        try{
            if(id!=null){
                productService.delete(productService.queryByPK(id));
            }else {
                Long[] ss = JsonUtil.json2Obj(ids,Long[].class);
                for (Long _id : ss) {
                    productService.delete(productService.queryByPK(_id));
                }
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

    /**
     * 众筹-推荐到许愿池
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "/wishingWell")
    @ResponseBody
    public Result wishingWell(Long id,Integer status){
        if(id==null){
            return Result.failure();
        }
        try{
            List<Product> list = productService.queryByProperty("wishingWell",1);
            if(!list.isEmpty() &&list.size()>0){
                Product _p = list.get(0);
                _p.setWishingWell(0);
                productService.update(_p);
            }
            Product p = productService.queryByPK(id);
            p.setWishingWell(status);
            productService.update(p);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }


    /**
     * 广告位-保存周期益米
     * @param id
     * @param days
     * @param adsYm
     */
    private void adsSave(Long id,String days,String adsYm){

        Integer[] dayes = JsonUtil.json2Obj(days, Integer[].class);
        Integer[] adsYms = JsonUtil.json2Obj(adsYm, Integer[].class);

        for(int i=0;i<dayes.length;i++){
            ProductAds productAds = new ProductAds();
            productAds.setDays(dayes[i]);
            productAds.setYm(adsYms[i]);
            productAds.setProductId(id);
            productAdsService.save(productAds);

        }
    }

    private void modelAdd(Long id,Model model){
        Product product = productService.queryByPK(id);
        model.addAttribute("product",product);
        if(product.getType()==2){
            List<ProductAds> productAdsList = productAdsService.queryByProperty("productId",id);
            if(!productAdsList.isEmpty() && productAdsList.size()>0){
                model.addAttribute("productAdsList",productAdsList);
            }
        }
    }

}
