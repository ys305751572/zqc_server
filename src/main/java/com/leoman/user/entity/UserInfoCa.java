package com.leoman.user.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/7/27.
 */
@Entity
@Table(name = "t_user_info_ca")
public class UserInfoCa extends BaseEntity{

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "ca_url")
    private String caUrl;

    @Column(name = "creator_id")
    private Long creatorId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCaUrl() {
        return caUrl;
    }

    public void setCaUrl(String caUrl) {
        this.caUrl = caUrl;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
