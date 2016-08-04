package com.leoman.product.entity;

import com.leoman.common.entity.BaseEntity;
import com.leoman.utils.ConfigUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 商品表
 *
 * Created by Administrator on 2016/8/1.
 */
@Entity
@Table(name = "t_product")
public class Product extends BaseEntity{
    //商品类型 0:实物 1:众筹 2:广告位
    @Column(name = "type")
    private Integer type;
    //商品名称
    @Column(name = "name")
    private String name;
    //简短描述
    @Column(name = "short_desc")
    private String shortDesc;
    //封面路径
    @Column(name = "cover_url")
    private String coverUrl;
    //详情图片
    @Column(name = "detail_image_url")
    private String detailImageUrl;
    //所需益米（type=1时，表示单人所需益米）
    @Column(name = "ym")
    private Integer ym;
    //type = 1时所需人数
    @Column(name = "nums")
    private Integer nums;
    //type = 1时已经众筹人数
    @Column(name = "buy_num")
    private Integer buyNum;
    //type = 1时是否推荐到许愿池,0: 没有 1:已推荐(许愿池只能有一条数据)
    @Column(name = "wishing_well")
    private Integer wishingWell;
    //开始有效期 type = 0
    @Column(name = "valid_start_date")
    private Long validStartDate;
    //结束有效期 type = 0
    @Column(name = "valid_end_date")
    private Long validEndDate;
    //兑换地址 type = 0
    @Column(name = "address")
    private String address;
    //描述
    @Column(name = "detail_desc")
    private String detailDesc;
    //众筹状态
    @Transient
    private String raiseStatus;
    //众筹益米
    @Transient
    private Integer zcym;

    public Integer getZcym() {
        if(getType()==1){
            return getYm()*getNums();
        }
        return 0;
    }

    public void setZcym(Integer zcym) {
        this.zcym = zcym;
    }

    public String getRaiseStatus() {
        if(getType()==1){
            if(getNums().equals(getBuyNum())){
                return "已结束";
            }else {
                return "进行中";
            }
        }
        return "商品类型不是众筹";
    }

    public void setRaiseStatus(String raiseStatus) {
        this.raiseStatus = raiseStatus;
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

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getCoverUrl() {
        return ConfigUtil.getString("upload.url") + this.coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getDetailImageUrl() {
        return ConfigUtil.getString("upload.url") + this.detailImageUrl;
    }

    public void setDetailImageUrl(String detailImageUrl) {
        this.detailImageUrl = detailImageUrl;
    }

    public Integer getYm() {
        return ym;
    }

    public void setYm(Integer ym) {
        this.ym = ym;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public Long getValidStartDate() {
        return validStartDate;
    }

    public void setValidStartDate(Long validStartDate) {
        this.validStartDate = validStartDate;
    }

    public Long getValidEndDate() {
        return validEndDate;
    }

    public void setValidEndDate(Long validEndDate) {
        this.validEndDate = validEndDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public Integer getWishingWell() {
        return wishingWell;
    }

    public void setWishingWell(Integer wishingWell) {
        this.wishingWell = wishingWell;
    }
}
