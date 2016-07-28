package com.leoman.admin.dao;


import com.leoman.admin.entity.Admin;
import com.leoman.common.dao.IBaseJpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;

/**
 * Created by Administrator on 2016/3/8.
 */
public interface AdminDao extends IBaseJpaRepository<Admin> {


    @Query("select a from Admin a where a.username = ?1")
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    public Admin findByUsername(String username);
}
