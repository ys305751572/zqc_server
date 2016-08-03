package com.leoman.record.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * Created by Administrator on 2016/8/3.
 */
@Entity
@Table(name = "t_ym_record")
public class YmRecord extends BaseEntity{

    //用户ID或团队id
    @Column(name = "join_id")
    private Long joinId;
    //益米变化内容
    @Column(name = "content")
    private String content;
    //增减益米数
    @Column(name = "ym")
    private Integer ym;
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

    public Integer getYm() {
        return ym;
    }

    public void setYm(Integer ym) {
        this.ym = ym;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
