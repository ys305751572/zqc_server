package com.leoman.team.service;

import com.leoman.common.service.GenericManager;
import com.leoman.team.entity.Team;
import com.leoman.user.entity.UserInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
public interface TeamService extends GenericManager<Team> {

    public List<UserInfo> findTeamMember(Long teamId);

    public Page<Team> findPage(Team team, String sort, int currnetPage, int pagesize);
}
