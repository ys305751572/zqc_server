package com.leoman.product.service;

import com.leoman.common.service.GenericManager;
import com.leoman.product.entity.ProductAds;

/**
 *
 * Created by Administrator on 2016/8/1.
 */
public interface ProductAdsService extends GenericManager<ProductAds>{

    //广告位-保存周期益米
    public void adsSave(Long id,String days,String adsYm);
}
