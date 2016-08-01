package com.leoman.product.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * 商品表(广告位)
 * Created by Administrator on 2016/8/1.
 */
@Entity
@Table(name = "t_product_ads")
public class ProductAds extends BaseEntity{
    //商品ID
    @Column(name= "product_id")
    private Long productId;

    //周期 这里保存天数
    @Column(name = "days")
    private Integer days;

    //所需益米
    @Column(name = "ym")
    private Integer ym;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getYm() {
        return ym;
    }

    public void setYm(Integer ym) {
        this.ym = ym;
    }
}
