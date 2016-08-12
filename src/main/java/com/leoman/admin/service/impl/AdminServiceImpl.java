package com.leoman.admin.service.impl;

import com.leoman.admin.dao.AdminDao;
import com.leoman.admin.entity.Admin;
import com.leoman.admin.service.AdminService;
import com.leoman.common.core.Constant;
import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.utils.Md5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
@Service
public class AdminServiceImpl extends GenericManagerImpl<Admin, AdminDao> implements AdminService {

    @Autowired
    private AdminDao dao;

    @Override
    public Admin findByUsername(String username) {
        return dao.findByUsername(username);
    }

    @Override
    public Page<Admin> findAll(Admin admin, Integer currentPage, Integer pageSize) throws Exception {
        Specification<Admin> spec = buildSpecification(admin);
        return dao.findAll(spec, new PageRequest(currentPage - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    @Transactional
    public void resetPassword(Long id){

        Admin admin = this.queryByPK(id);
        admin.setPassword(Md5Util.md5(Constant.DEFAULT_PASSWORD));
        this.save(admin);

    }

    public Specification<Admin> buildSpecification(final Admin u) {
        return new Specification<Admin>() {
            @Override
            public Predicate toPredicate(Root<Admin> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (StringUtils.isNotBlank(u.getUsername())) {
                    list.add(cb.like(root.get("username").as(String.class), "%" + u.getUsername() + "%"));
                }
                if (StringUtils.isNotBlank(u.getMobile())) {
                    list.add(cb.like(root.get("mobile").as(String.class), "%" + u.getMobile() + "%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
    }
}
