package com.leoman.dynamic.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.dynamic.dao.DynamicImageDao;
import com.leoman.dynamic.entity.DynamicImage;
import com.leoman.dynamic.service.DynamicImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 * Created by Administrator on 2016/7/28.
 */
@Service
public class DynamicImageServiceImpl extends GenericManagerImpl<DynamicImage,DynamicImageDao> implements DynamicImageService{

    @Autowired
    private DynamicImageDao dao;

    @Autowired
    private EntityManagerFactory factory;

    @Override
    public void updateDynamicId(String imageIds,Long dynamicId) {
        String sql = "update t_dynamic_image set dynamic_id = " + dynamicId + " where id in (" + imageIds + ")";
        EntityManager em = factory.createEntityManager();
        em.joinTransaction();
        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }
}
