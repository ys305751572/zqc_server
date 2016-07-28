package com.leoman.user.service;

import com.leoman.common.service.GenericManager;
import com.leoman.user.entity.UserInfo;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/7/22.
 */
public interface UserInfoService extends GenericManager<UserInfo>{

    public Page<UserInfo> findPage(UserInfo userInfo,int currentPage,int pageSize);

    public void saveUserInfo(UserInfo userInfo,String imageIds);
}
