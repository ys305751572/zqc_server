package com.leoman.security.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.security.dao.RoleDao;
import com.leoman.security.entity.Role;
import com.leoman.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/13.
 */
@Service
public class RoleServiceImpl extends GenericManagerImpl<Role,RoleDao> implements RoleService{

    @Autowired
    private RoleDao dao;

}
