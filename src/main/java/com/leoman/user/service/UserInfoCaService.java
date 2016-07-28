package com.leoman.user.service;

import com.leoman.common.service.GenericManager;
import com.leoman.user.entity.UserInfoCa;

/**
 * Created by Administrator on 2016/7/27.
 */
public interface UserInfoCaService extends GenericManager<UserInfoCa>{

    public void modifyCaUserId(Long userId,String imageIds);
}
