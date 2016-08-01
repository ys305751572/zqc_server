package com.leoman.product.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.product.dao.ProductAdsDao;
import com.leoman.product.entity.ProductAds;
import com.leoman.product.service.ProductAdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/8/1.
 */
@Service
public class ProductAdsServiceImpl extends GenericManagerImpl<ProductAds,ProductAdsDao> implements ProductAdsService{

    @Autowired
    private ProductAdsDao dao;

}
