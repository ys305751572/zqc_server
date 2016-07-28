package com.leoman.velocity.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.velocity.dao.DDDAO;
import com.leoman.velocity.entity.DD;
import com.leoman.velocity.model.ValidateRule;
import com.leoman.velocity.service.DDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
@Service
public class DDServiceImpl extends GenericManagerImpl<DD,DDDAO> implements DDService{

    @Autowired
    private DDDAO dao;

    @Override
    public List<ValidateRule> findAllValidateRule() {
        return Arrays.asList(ValidateRule.values());
    }
}
