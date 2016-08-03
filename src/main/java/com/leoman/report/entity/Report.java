package com.leoman.report.entity;

import com.leoman.common.entity.BaseEntity;
import com.leoman.user.entity.UserInfo;

import javax.persistence.*;

/**
 * 一键反馈表
 * Created by Administrator on 2016/8/3.
 */
@Entity
@Table(name = "t_report")
public class Report extends BaseEntity{
    //反馈用户ID
    @ManyToOne
    @JoinColumn(name= "user_id")
    private UserInfo userInfo;
    //反馈内容
    @Column(name = "content")
    private String content;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
