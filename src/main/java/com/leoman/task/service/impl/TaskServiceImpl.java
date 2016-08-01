package com.leoman.task.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.task.dao.TaskDao;
import com.leoman.task.entity.Task;
import com.leoman.task.service.TaskService;
import com.leoman.utils.DateUtils;
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
 * 任务
 * Created by Administrator on 2016/7/28.
 */
@Service
public class TaskServiceImpl extends GenericManagerImpl<Task,TaskDao> implements TaskService{

    @Autowired
    private TaskDao dao;

    @Override
    public Page<Task> findPage(final Task task,final Integer taskStatus,final Integer checkpointStatus, int currnetPage, int pagesize) {
        Specification<Task> spec = new Specification<Task>() {
            @Override
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                //类型：1-益起来任务，2-脑洞虚设任务
                if(task.getType()!=null){
                    list.add(criteriaBuilder.equal(root.get("type").as(Integer.class), task.getType()));
                }

                if(StringUtils.isNotBlank(task.getName())){
                    list.add(criteriaBuilder.like(root.get("name").as(String.class), "%"+task.getName()+"%"));
                }

                if(task.getJoinType()!=null){
                    list.add(criteriaBuilder.equal(root.get("joinType").as(Integer.class), task.getJoinType()));
                }

                if(taskStatus!=null){
                    //gt大于
                    if(taskStatus==0){
                        list.add(criteriaBuilder.gt(root.get("startDate").as(Long.class), System.currentTimeMillis()));
                    }
                    //lt小于
                    if(taskStatus==1){
                        list.add(criteriaBuilder.lt(root.get("endDate").as(Long.class), System.currentTimeMillis()));
                    }
                    if(taskStatus==2){
                        list.add(criteriaBuilder.and(criteriaBuilder.lt(root.get("startDate").as(Long.class), System.currentTimeMillis()),criteriaBuilder.gt(root.get("endDate").as(Long.class), System.currentTimeMillis())));
                    }
                }
                if(checkpointStatus!=null){
                    if(checkpointStatus==1){
                        list.add(criteriaBuilder.lt(root.get("createDate").as(Long.class), DateUtils.getTimesmorning()));
                    }
                    if(checkpointStatus==2){
                        list.add(criteriaBuilder.and(criteriaBuilder.gt(root.get("createDate").as(Long.class), DateUtils.getTimesmorning()),criteriaBuilder.lt(root.get("createDate").as(Long.class), DateUtils.getTimesnight())));
                    }
                }

                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec, new PageRequest(currnetPage - 1, pagesize, Sort.Direction.DESC,"id"));
    }
}
