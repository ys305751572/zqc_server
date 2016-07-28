package com.leoman.dynamic.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/7/28.
 */
@Table(name = "t_dynamic_image")
@Entity
public class DynamicImage extends BaseEntity{

    @Column(name = "dynamic_id")
    private Long dynamicId;

    @Column(name = "image_url")
    private String imageUrl;

    public Long getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Long dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
