package com.leoman.user.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.user.dao.UserInfoDao;
import com.leoman.user.entity.UserInfo;
import com.leoman.user.service.UserInfoCaService;
import com.leoman.user.service.UserInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/22.
 */
@Service
public class UserInfoServiceImpl extends GenericManagerImpl<UserInfo,UserInfoDao> implements UserInfoService{

    @Autowired
    private UserInfoDao dao;

    @Autowired
    private UserInfoCaService userInfoCaService;

    @Override
    public Page<UserInfo> findPage(final UserInfo userInfo, int currentPage, int pageSize) {

        Specification<UserInfo> spec = new Specification<UserInfo>() {
            @Override
            public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(userInfo.getMobile())) {
                    list.add(criteriaBuilder.like(root.get("mobile").as(String.class),"%" + userInfo.getMobile() + "%"));
                }
                if(StringUtils.isNotBlank(userInfo.getNickname())) {
                    list.add(criteriaBuilder.like(root.get("nickname").as(String.class),"%" + userInfo.getNickname() + "%"));
                }
                if(StringUtils.isNotBlank(userInfo.getIdCard())) {
                    list.add(criteriaBuilder.like(root.get("idCard").as(String.class),"%" + userInfo.getIdCard() + "%"));
                }
                if(userInfo.getGender() != null) {
                    list.add(criteriaBuilder.equal(root.get("gender").as(Integer.class),userInfo.getGender()));
                }
                if(userInfo.getStatus() != null) {
                    list.add(criteriaBuilder.equal(root.get("status").as(Integer.class),userInfo.getStatus()));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec,new PageRequest(currentPage - 1,pageSize, Sort.Direction.DESC,"id"));
    }

    @Transactional
    @Override
    public void saveUserInfo(UserInfo userInfo, String imageIds) {
        this.save(userInfo);
        userInfoCaService.modifyCaUserId(userInfo.getId(),imageIds);
    }
}
