package com.leoman.task.entity;

import com.leoman.common.entity.BaseEntity;
import com.leoman.team.entity.Team;
import com.leoman.user.entity.UserInfo;

import javax.persistence.*;

/**
 * 任务-报名表
 * Created by Administrator on 2016/7/29.
 */
@Entity
@Table(name = "t_task_join")
public class TaskJoin extends BaseEntity{

    //任务ID
    @Column(name = "task_id")
    private Long taskId;

    //报名 用户/团队ID
    @Column(name = "join_id")
    private Long joinId;

    //0-未处理，1-通过，2-不通过
    @Column(name = "status")
    private Integer status;

    //是否分配（1-已分配, 0-未分配)
    @Column(name = "is_allot")
    private Integer isAllot;

    @Transient
    private UserInfo userinfo;

    @Transient
    private Team team;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getJoinId() {
        return joinId;
    }

    public void setJoinId(Long joinId) {
        this.joinId = joinId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsAllot() {
        return isAllot;
    }

    public void setIsAllot(Integer isAllot) {
        this.isAllot = isAllot;
    }

    public UserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
