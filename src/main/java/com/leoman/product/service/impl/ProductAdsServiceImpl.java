package com.leoman.product.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.product.dao.ProductAdsDao;
import com.leoman.product.entity.ProductAds;
import com.leoman.product.service.ProductAdsService;
import com.leoman.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/8/1.
 */
@Service
@Transactional
public class ProductAdsServiceImpl extends GenericManagerImpl<ProductAds,ProductAdsDao> implements ProductAdsService{

    @Autowired
    private ProductAdsDao dao;

    @Override
    public void adsSave(Long id, String days, String adsYm) {

        Integer[] dayes = JsonUtil.json2Obj(days, Integer[].class);
        Integer[] adsYms = JsonUtil.json2Obj(adsYm, Integer[].class);

        for(int i=0;i<dayes.length;i++){
            ProductAds productAds = new ProductAds();
            productAds.setDays(dayes[i]);
            productAds.setYm(adsYms[i]);
            productAds.setProductId(id);
            dao.save(productAds);
        }
    }
}
