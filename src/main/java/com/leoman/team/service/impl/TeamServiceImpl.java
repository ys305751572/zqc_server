package com.leoman.team.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.team.dao.TeamDao;
import com.leoman.team.entity.Team;
import com.leoman.team.entity.TeamUser;
import com.leoman.team.service.TeamService;
import com.leoman.team.service.TeamUserService;
import com.leoman.user.entity.UserInfo;
import com.leoman.user.service.UserInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
@Service
public class TeamServiceImpl extends GenericManagerImpl<Team, TeamDao> implements TeamService {

    @Autowired
    private TeamDao dao;

    @Autowired
    private TeamUserService teamUserService;

    @Override
    public List<UserInfo> findTeamMember(Long teamId) {

        List<TeamUser> teamUsers = teamUserService.queryByProperty("teamId", teamId);
        if (teamUsers == null && teamUsers.isEmpty()) return Collections.emptyList();

        List<UserInfo> userInfos = new ArrayList<UserInfo>();
        for (TeamUser teamUser : teamUsers) {
            userInfos.add(teamUser.getUserInfo());
        }
        return userInfos;
    }

    @Override
    public Page<Team> findPage(final Team team, final String sort, int currnetPage, int pagesize) {
        Specification<Team> spec = new Specification<Team>() {
            @Override
            public Predicate toPredicate(Root<Team> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (StringUtils.isNotBlank(team.getName())) {
                    list.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + team.getName() + "%"));
                }
                if (StringUtils.isNotBlank(team.getSlogan())) {
                    list.add(criteriaBuilder.like(root.get("slogan").as(String.class), "%" + team.getSlogan() + "%"));
                }
                if (StringUtils.isNotBlank(team.getUserinfo().getNickname())) {
                    list.add(criteriaBuilder.like(root.get("userinfo").get("nickname").as(String.class), "%" + team.getUserinfo().getNickname() + "%"));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec, new PageRequest(currnetPage - 1, pagesize, Sort.Direction.DESC, sort));
    }
}
