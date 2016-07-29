package com.leoman.task.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.task.dao.TaskDao;
import com.leoman.task.dao.TaskJoinDao;
import com.leoman.task.entity.Task;
import com.leoman.task.entity.TaskJoin;
import com.leoman.task.service.TaskJoinService;
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
 * Created by Administrator on 2016/7/29.
 */
@Service
public class TaskJoinServiceImpl extends GenericManagerImpl<TaskJoin,TaskJoinDao> implements TaskJoinService{

    @Autowired
    private TaskJoinDao dao;

    @Override
    public Page<TaskJoin> findPage(final String mobileName,final String nickName,final TaskJoin taskJoin, int currnetPage, int pagesize) {
        Specification<TaskJoin> spec = new Specification<TaskJoin>() {

            @Override
            public Predicate toPredicate(Root<TaskJoin> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                Predicate p = null;
                if(taskJoin.getTaskId()!=null){
                    list.add(criteriaBuilder.equal(root.get("taskId").as(Integer.class), taskJoin.getTaskId()));
                }

                if(StringUtils.isNotBlank(mobileName)){
                    p = criteriaBuilder.like(root.get("userinfo").get("mobile").as(String.class), "%"+mobileName+"%");
                    p = criteriaBuilder.or(p,criteriaBuilder.like(root.get("team").get("name").as(String.class), "%"+mobileName+"%"));
                    list.add(p);
//                    list.add(criteriaBuilder.or(criteriaBuilder.like(root.get("userinfo").get("mobile").as(String.class), "%"+mobileName+"%"),criteriaBuilder.like(root.get("team").get("name").as(String.class), "%"+mobileName+"%")));
                }

                if(StringUtils.isNotBlank(nickName)){
                    p = criteriaBuilder.like(root.get("userinfo").get("nickname").as(String.class), "%"+mobileName+"%");
                    p = criteriaBuilder.or(p,criteriaBuilder.like(root.get("team").get("userinfo").get("nickname").as(String.class), "%"+mobileName+"%"));
                    list.add(p);
                }

                if(taskJoin.getStatus()!=null){
                    list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), taskJoin.getStatus()));
                }

                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec, new PageRequest(currnetPage - 1, pagesize, Sort.Direction.DESC,"id"));
    }
}
