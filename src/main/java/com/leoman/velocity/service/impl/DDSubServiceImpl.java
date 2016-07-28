package com.leoman.velocity.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.velocity.dao.DDSubDao;
import com.leoman.velocity.entity.DDSub;
import com.leoman.velocity.service.DDSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/28.
 */
@Service
public class DDSubServiceImpl extends GenericManagerImpl<DDSub,DDSubDao> implements DDSubService{

    @Autowired
    private DDSubDao dao;
}
