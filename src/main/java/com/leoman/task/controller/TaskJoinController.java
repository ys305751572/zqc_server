package com.leoman.task.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.task.entity.Task;
import com.leoman.task.entity.TaskJoin;
import com.leoman.task.service.TaskJoinService;
import com.leoman.task.service.TaskService;
import com.leoman.task.service.impl.TaskJoinServiceImpl;
import com.leoman.team.entity.Team;
import com.leoman.team.service.TeamService;
import com.leoman.user.entity.UserInfo;
import com.leoman.user.service.UserInfoService;
import com.leoman.utils.JsonUtil;
import com.leoman.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private TaskService taskService;
    @Autowired
    private TaskJoinService taskJoinService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/index")
    public String index(Long taskId, Integer joinType, Model model){
        Task task = taskService.queryByPK(taskId);
        model.addAttribute("task",task);
        model.addAttribute("joinType",joinType);
        return "taskjoin/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> list(TaskJoin taskJoin, Integer start, Integer length, Integer draw, Integer joinType,String mobile,String teamName,String nickName) {
        int currentPage = getPageNum(start, length);
        Page<TaskJoin> page = taskJoinService.findPage(mobile,teamName,nickName,taskJoin, currentPage, length);
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

    /**
     * 审核-成功增加积分
     * @param id
     * @param status
     * @param rewardYm
     * @param rewardIntegral
     * @param joinType
     * @return
     */
    @RequestMapping(value = "/status")
    @ResponseBody
    public Result status(Long id, Integer status,Integer rewardYm,Integer rewardIntegral,Integer joinType){
        try{
            this.audit(status,rewardYm,rewardIntegral,joinType,id);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

    @RequestMapping(value = "/batchDel", method = RequestMethod.POST)
    @ResponseBody
    public Result batchDel(String ids,Integer status,Integer rewardYm,Integer rewardIntegral,Integer joinType) {
        if (StringUtils.isBlank(ids)){
            return Result.failure();
        }
        try {
            Long[] ss = JsonUtil.json2Obj(ids,Long[].class);
            for (Long id : ss) {
                this.audit(status,rewardYm,rewardIntegral,joinType,id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success();
    }

    private void audit(Integer status,Integer rewardYm,Integer rewardIntegral,Integer joinType,Long id){
        TaskJoin taskJoin = taskJoinService.queryByPK(id);
        taskJoin.setStatus(status);
        taskJoinService.update(taskJoin);
        if(status==1){
            //个人
            if(joinType==0){
                UserInfo userInfo = userInfoService.queryByPK(taskJoin.getJoinId());
                userInfo.setYm(userInfo.getYm()+rewardYm);
                userInfo.setIntegral(userInfo.getIntegral()+rewardIntegral);
                userInfoService.update(userInfo);
            }
            //团队
            if(joinType==1){
                Team team = teamService.queryByPK(taskJoin.getJoinId());
                team.setYm(team.getYm()+rewardYm);
                team.setIntegral(team.getIntegral()+rewardIntegral);
                teamService.update(team);
            }
        }

    }

}
