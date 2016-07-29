package com.leoman.task.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.task.entity.Task;
import com.leoman.task.entity.TaskJoin;
import com.leoman.task.service.TaskJoinService;
import com.leoman.task.service.impl.TaskJoinServiceImpl;
import com.leoman.team.entity.Team;
import com.leoman.team.service.TeamService;
import com.leoman.user.entity.UserInfo;
import com.leoman.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by Administrator on 2016/7/29.
 */
@Controller
@RequestMapping(value = "/admin/taskJoin")
public class TaskJoinController extends GenericEntityController<TaskJoin,TaskJoin,TaskJoinServiceImpl> {

    @Autowired
    private TaskJoinService taskJoinService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/index")
    public String index(Long taskId, Integer joinType, Model model){
        model.addAttribute("taskId",taskId);
        model.addAttribute("joinType",joinType);
        return "taskjoin/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> list(TaskJoin taskJoin, Integer start, Integer length, Integer draw, Integer joinType,String mobileName,String nickName) {
        int currentPage = getPageNum(start, length);
        Page<TaskJoin> page = taskJoinService.findPage(mobileName,nickName,taskJoin, currentPage, length);
        List<TaskJoin> list = page.getContent();
        if(joinType==0){
            for(TaskJoin tj : list){
                UserInfo userInfo = userInfoService.queryByPK(tj.getJoinId());
                tj.setUserinfo(userInfo);
            }
        }
        else {
            for(TaskJoin tj : list){
                Team team = teamService.queryByPK(tj.getJoinId());
                tj.setTeam(team);
            }

        }
        return DataTableFactory.fitting(draw, page);
    }

}