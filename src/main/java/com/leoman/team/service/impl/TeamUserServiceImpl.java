package com.leoman.team.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.team.dao.TeamUserDao;
import com.leoman.team.entity.TeamUser;
import com.leoman.team.service.TeamUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/7/27.
 */
@Service
public class TeamUserServiceImpl extends GenericManagerImpl<TeamUser,TeamUserDao> implements TeamUserService{

    @Autowired
    private TeamUserDao dao;
}
