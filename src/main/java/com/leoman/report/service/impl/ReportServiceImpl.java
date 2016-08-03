package com.leoman.report.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.report.dao.ReportDao;
import com.leoman.report.entity.Report;
import com.leoman.report.service.ReportService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Administrator on 2016/8/3.
 */
@Service
public class ReportServiceImpl extends GenericManagerImpl<Report,ReportDao> implements ReportService{

    @Autowired
    private ReportDao dao;

    @Override
    public Page<Report> findPage(final Report report, int currnetPage, int pagesize) {
        Specification<Report> spec = new Specification<Report>() {
            @Override
            public Predicate toPredicate(Root<Report> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();

                if(report.getUserInfo()!=null && StringUtils.isNotBlank(report.getUserInfo().getMobile())){
                    list.add(criteriaBuilder.like(root.get("userInfo").get("mobile").as(String.class),"%"+report.getUserInfo().getMobile()+"%"));
                }

                if(StringUtils.isNotBlank(report.getContent())){
                    list.add(criteriaBuilder.like(root.get("content").as(String.class),"%"+report.getContent()+"%"));
                }

                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec,new PageRequest(currnetPage - 1, pagesize, Sort.Direction.DESC,"id"));
    }
}
