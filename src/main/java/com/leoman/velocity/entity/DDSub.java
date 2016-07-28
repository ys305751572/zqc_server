package com.leoman.velocity.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/6/28.
 */
@Entity
@Table(name = "t_ddsub")
public class DDSub extends BaseEntity{

    @Column(name = "dd_id")
    private Long ddId;

    @Column(name = "key1")
    private String key;

    @Column(name = "value1")
    private String value;

    public Long getDdId() {
        return ddId;
    }

    public void setDdId(Long ddId) {
        this.ddId = ddId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
