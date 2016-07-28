package com.leoman.dynamic.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.dynamic.dao.DynamicDao;
import com.leoman.dynamic.entity.Dynamic;
import com.leoman.dynamic.service.DynamicImageService;
import com.leoman.dynamic.service.DynamicService;
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
 * Created by Administrator on 2016/7/28.
 */
@Service
public class DynamicServiceImpl extends GenericManagerImpl<Dynamic, DynamicDao> implements DynamicService {

    @Autowired
    private DynamicDao dao;

    @Autowired
    private DynamicImageService dynamicImageService;


    @Override
    public Page<Dynamic> findPage(final Dynamic dynamic, int currentPage, int pagesize) {

        Specification<Dynamic> spec = new Specification<Dynamic>() {
            @Override
            public Predicate toPredicate(Root<Dynamic> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> list = new ArrayList<Predicate>();
                if (StringUtils.isNotBlank(dynamic.getContent())) {
                    list.add(criteriaBuilder.like(root.get("content").as(String.class), "%" + dynamic.getContent() + "%"));
                }
                if (dynamic.getStatus() != null) {
                    list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), dynamic.getStatus()));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec, new PageRequest(currentPage - 1, pagesize, Sort.Direction.DESC, "id"));
    }

    @Override
    @Transactional
    public void saveDynamic(Dynamic dynamic, String imageIds) {
        this.save(dynamic);
        dynamicImageService.updateDynamicId(imageIds,dynamic.getId());
    }
}
