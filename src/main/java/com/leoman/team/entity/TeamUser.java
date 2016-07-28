package com.leoman.team.entity;

import com.leoman.common.entity.BaseEntity;
import com.leoman.user.entity.UserInfo;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/7/27.
 */
@Entity
@Table(name = "t_team_user")
public class TeamUser extends BaseEntity{

    @Column(name = "team_id")
    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
