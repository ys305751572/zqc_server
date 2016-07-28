package com.leoman.velocity.service;

import com.leoman.common.service.GenericManager;
import com.leoman.velocity.entity.DD;
import com.leoman.velocity.model.ValidateRule;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public interface DDService extends GenericManager<DD>{


    public List<ValidateRule> findAllValidateRule();
}
