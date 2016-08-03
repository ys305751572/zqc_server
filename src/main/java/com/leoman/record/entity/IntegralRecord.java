package com.leoman.record.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 积分记录表
 * Created by Administrator on 2016/8/3.
 */
@Entity
@Table(name = "t_integral_record")
public class IntegralRecord extends BaseEntity{

    //用户ID或团队id
    @Column(name = "join_id")
    private Long joinId;
    //益米变化内容
    @Column(name = "content")
    private String content;
    //增减经验数
    @Column(name = "integral")
    private Integer integral;
    //类型 0:个人 1:团队
    @Column(name = "type")
    private Integer type;

    public Long getJoinId() {
        return joinId;
    }

    public void setJoinId(Long joinId) {
        this.joinId = joinId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
