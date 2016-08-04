package com.leoman.task.entity;

import com.leoman.common.entity.BaseEntity;
import com.leoman.utils.ConfigUtil;
import com.leoman.utils.DateUtil;
import com.leoman.utils.DateUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 任务表
 * Created by Administrator on 2016/7/28.
 */
@Entity
@Table(name = "t_task")
public class Task extends BaseEntity{

    //类型：1-益起来任务，2-脑洞虚设任务(新加)
    @Column(name = "type")
    private Integer type;

    //任务名称
    @Column(name = "name")
    private String name;

    //封面路径
    @Column(name = "cover_url")
    private String coverUrl;

    //活动类型 0:个人 1:团队（新改）
    @Column(name = "join_type")
    private Integer joinType;

    //任务开始时间
    @Column(name = "start_date")
    private Long startDate;

    //任务结束时间
    @Column(name = "end_date")
    private Long endDate;

    //活动地点
    @Column(name = "address")
    private String address;

    //主办方
    @Column(name = "organizers")
    private String organizers;

    //所需人数
    @Column(name = "nums")
    private Integer nums;

    //已报名人数
    @Column(name = "join_num")
    private Integer joinNum;

    //奖励益米
    @Column(name = "reward_ym")
    private Integer rewardYm;

    //奖励积分
    @Column(name = "reward_integral")
    private Integer rewardIntegral;

    //标题
    @Column(name = "title")
    private String title;

    //详情
    @Column(name = "detail")
    private String detail;

    //创建者ID
    @Column(name = "creator_id")
    private Long creatorId;

    //关卡
    @Column(name = "checkpoint")
    private String checkpoint;

    //yql任务状态
    @Transient
    private String taskStatus;

    //ndxs任务状态
    @Transient
    private String checkpointStatus;

    public String getTaskStatus() {
        if(getStartDate()!=null && getEndDate()!=null){
            if(this.getStartDate()<System.currentTimeMillis() && this.getEndDate()>System.currentTimeMillis()){
                return "进行中";
            }else if(this.getStartDate()>System.currentTimeMillis()){
                return "待开始";
            }else if(this.getEndDate()<System.currentTimeMillis()){
                return "已结束";
            }
        }
        return "任务时间不存在";
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getCheckpointStatus() {
        if(getCreateDate()!=null){
            if(getCreateDate()> DateUtils.getTimesmorning() && getCreateDate()< DateUtils.getTimesnight()){
                return "进行中";
            }else {
                return "已结束";
            }
        }
        return "新增时间不存在";
    }

    public void setCheckpointStatus(String checkpointStatus) {
        this.checkpointStatus = checkpointStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverUrl() {
        return ConfigUtil.getString("upload.url") + this.coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Integer getJoinType() {
        return joinType;
    }

    public void setJoinType(Integer joinType) {
        this.joinType = joinType;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrganizers() {
        return organizers;
    }

    public void setOrganizers(String organizers) {
        this.organizers = organizers;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Integer getJoinNum() {
        return joinNum;
    }

    public void setJoinNum(Integer joinNum) {
        this.joinNum = joinNum;
    }

    public Integer getRewardYm() {
        return rewardYm;
    }

    public void setRewardYm(Integer rewardYm) {
        this.rewardYm = rewardYm;
    }

    public Integer getRewardIntegral() {
        return rewardIntegral;
    }

    public void setRewardIntegral(Integer rewardIntegral) {
        this.rewardIntegral = rewardIntegral;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(String checkpoint) {
        this.checkpoint = checkpoint;
    }
}
