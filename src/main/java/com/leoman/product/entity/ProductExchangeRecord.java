package com.leoman.product.entity;

import com.leoman.common.entity.BaseEntity;
import com.leoman.team.entity.Team;
import com.leoman.user.entity.UserInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 商品兑换记录表
 * Created by Administrator on 2016/8/3.
 */
@Entity
@Table(name = "t_product_exchange_record")
public class ProductExchangeRecord extends BaseEntity{
    //兑换用户类型：0-个人，1-团队
    @Column(name = "join_type")
    private Integer joinType;
    //兑换用户ID或团队id
    @Column(name = "join_id")
    private Long joinId;
    //手机/团体名称
    @Column(name = "name")
    private String name;
    //昵称
    @Column(name = "nickname")
    private String nickname;
    //商品名称
    @Column(name = "product_name")
    private String productName;
    //商品类型0:实物 1:众筹 2:广告位
    @Column(name = "product_type")
    private Integer productType;
    //商品类型名称
    @Column(name = "product_type_name")
    private String productTypeName;
    //兑换积分
    @Column(name = "integral")
    private Integer integral;
    //状态0:未处理 1:成功 2:失败 3:过期
    @Column(name = "status")
    private Integer status;
    //周期 这里保存天数
    @Column(name = "days")
    private Integer days;
    //所需益米
    @Column(name = "ym")
    private Integer ym;
    //兑换码
    @Column(name = "code")
    private String code;
    //有效期开始时间
    @Column(name = "valid_start_date")
    private Long validStartDate;
    //有效期结束时间
    @Column(name = "valid_end_date")
    private Long validEndDate;
    //兑换地址
    @Column(name = "address")
    private String address;

    @Transient
    private UserInfo userinfo;

    @Transient
    private Team team;

    public UserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getJoinType() {
        return joinType;
    }

    public void setJoinType(Integer joinType) {
        this.joinType = joinType;
    }

    public Long getJoinId() {
        return joinId;
    }

    public void setJoinId(Long joinId) {
        this.joinId = joinId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
