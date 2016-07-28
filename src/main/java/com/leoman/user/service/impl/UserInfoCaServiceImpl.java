package com.leoman.user.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.user.dao.UserInfoCaDao;
import com.leoman.user.entity.UserInfoCa;
import com.leoman.user.service.UserInfoCaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 * Created by Administrator on 2016/7/27.
 */
@Service
public class UserInfoCaServiceImpl extends GenericManagerImpl<UserInfoCa,UserInfoCaDao> implements UserInfoCaService{

    @Autowired
    private UserInfoCaDao dao;

    @Autowired
    private EntityManagerFactory factory;

    @Override
    public void modifyCaUserId(Long userId, String imageIds) {
        EntityManager em = null;
        try {
            if(StringUtils.isNotBlank(imageIds)) {
                String sql = "update t_user_info_ca set user_id = " + userId + " where id in (" + imageIds + ")";
                em = factory.createEntityManager();
                em.joinTransaction();
                Query query = em.createNativeQuery(sql);
                query.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(em != null) {
                em.close();
            }
        }
    }
}
