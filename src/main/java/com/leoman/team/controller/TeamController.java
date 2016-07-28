package com.leoman.team.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.team.entity.Team;
import com.leoman.team.service.TeamService;
import com.leoman.team.service.impl.TeamServiceImpl;
import com.leoman.user.entity.UserInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2016/7/27.
 */
@RequestMapping("/admin/team")
@Controller
public class TeamController extends GenericEntityController<Team, Team, TeamServiceImpl> {

    @Autowired
    private TeamService teamService;

    /**
     * 团队列表页面
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "team/list";
    }

    /**
     * 团队列表查询
     *
     * @param team
     * @param start
     * @param length
     * @param draw
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findPage(Team team,String nickname, String sort, Integer start, Integer length, Integer draw) {
        int currentPage = getPageNum(start, length);
        UserInfo userInfo = new UserInfo();
        userInfo.setNickname(nickname);
        team.setUserinfo(userInfo);
        Page<Team> page = teamService.findPage(team, StringUtils.isBlank(sort) ? "id" : sort, currentPage, length);
        return DataTableFactory.fitting(draw, page);
    }

    /**
     * 团队详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Long id, Model model) {
        model.addAttribute("team", teamService.queryByPK(id));
        model.addAttribute("list", teamService.findTeamMember(id));
        return "team/detail";
    }
}
