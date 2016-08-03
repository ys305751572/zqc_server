package com.leoman.task.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.record.entity.IntegralRecord;
import com.leoman.record.entity.YmRecord;
import com.leoman.record.service.IntegralRecordService;
import com.leoman.record.service.YmRecordService;
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
 * 任务-报名表
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
    @Autowired
    private IntegralRecordService integralRecordService;
    @Autowired
    private YmRecordService ymRecordService;

    /**
     * 页面
     * @param taskId
     * @param joinType
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(Long taskId, Integer joinType, Model model){
        Task task = taskService.queryByPK(taskId);
        model.addAttribute("task",task);
        model.addAttribute("joinType",joinType);
        return "taskjoin/list";
    }

    /**
     * 列表
     * @param taskJoin
     * @param start
     * @param length
     * @param draw
     * @param joinType
     * @param mobile
     * @param teamName
     * @param nickName
     * @return
     */
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
     * 审核
     * @param id
     * @param ids
     * @param status
     * @param rewardYm
     * @param rewardIntegral
     * @param joinType
     * @return
     */
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    @ResponseBody
    public Result audit(Long id,String ids,Integer status,Integer rewardYm,Integer rewardIntegral,Integer joinType) {
        if (StringUtils.isBlank(ids) && id==null){
            return Result.failure();
        }
        try {
            if(id!=null){
                this.status(status,rewardYm,rewardIntegral,joinType,id);
            }else {
                Long[] ss = JsonUtil.json2Obj(ids,Long[].class);
                for (Long _id : ss) {
                    TaskJoin _t = taskJoinService.queryByPK(_id);
                    if(_t.getStatus()==0){
                        this.status(status,rewardYm,rewardIntegral,joinType,_id);
                    }else {
                        Result r = new Result();
                        r.setMsg("不能对已审核过任务的再次操作!");
                        r.setStatus(false);
                        return r;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success();
    }

    /**
     * 个人团队
     * @param status
     * @param rewardYm
     * @param rewardIntegral
     * @param joinType
     * @param id
     */
    private void status(Integer status,Integer rewardYm,Integer rewardIntegral,Integer joinType,Long id){
        TaskJoin taskJoin = taskJoinService.queryByPK(id);
        if(status==1){
            //个人
            if(joinType==0){
                UserInfo userInfo = userInfoService.queryByPK(taskJoin.getJoinId());
                userInfo.setYm(userInfo.getYm()+rewardYm);
                userInfo.setIntegral(userInfo.getIntegral()+rewardIntegral);
                userInfoService.update(userInfo);
                this.record(taskJoin,rewardYm,rewardIntegral,joinType);
            }
            //团队
            if(joinType==1){
                Team team = teamService.queryByPK(taskJoin.getJoinId());
                team.setYm(team.getYm()+rewardYm);
                team.setIntegral(team.getIntegral()+rewardIntegral);
                teamService.update(team);
                this.record(taskJoin,rewardYm,rewardIntegral,joinType);
            }
            taskJoin.setIsAllot(1);
        }
        taskJoin.setStatus(status);
        taskJoinService.update(taskJoin);

    }

    /**
     * 新增经验益米记录
     * @param taskJoin
     * @param rewardYm
     * @param rewardIntegral
     * @param joinType
     */
    private void record(TaskJoin taskJoin,Integer rewardYm,Integer rewardIntegral,Integer joinType){
        IntegralRecord integralRecord = new IntegralRecord();
        YmRecord ymRecord = new YmRecord();
        Task task = taskService.queryByPK(taskJoin.getTaskId());
        integralRecord.setJoinId(taskJoin.getJoinId());
        integralRecord.setIntegral(rewardIntegral);
        integralRecord.setType(joinType);
        ymRecord.setJoinId(taskJoin.getJoinId());
        ymRecord.setYm(rewardYm);
        ymRecord.setType(joinType);
        if(joinType==0){
            integralRecord.setContent("完成个人任务:["+task.getName()+"]所得积分");
            ymRecord.setContent("完成个人任务:["+task.getName()+"]所得益米");
        }else {
            integralRecord.setContent("完成团队任务:["+task.getName()+"]所得积分");
            ymRecord.setContent("完成团队任务:["+task.getName()+"]所得益米");
        }
        integralRecordService.save(integralRecord);
        ymRecordService.save(ymRecord);
    }

}
