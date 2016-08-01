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
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/7/29.
 */
@Service
public class TaskJoinServiceImpl extends GenericManagerImpl<TaskJoin,TaskJoinDao> implements TaskJoinService{

    @Autowired
    private TaskJoinDao dao;
    @Autowired
    private TaskDao taskDao;

    @Override
    public Page<TaskJoin> findPage(final String mobile,final String teamName,final String nickName,final TaskJoin taskJoin, int currnetPage, int pagesize) {
        Specification<TaskJoin> spec = new Specification<TaskJoin>() {

            @Override
            public Predicate toPredicate(Root<TaskJoin> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                Predicate p = null;
                if(taskJoin.getTaskId()!=null){
                    list.add(criteriaBuilder.equal(root.get("taskId").as(Integer.class), taskJoin.getTaskId()));
                }
                if(StringUtils.isNotBlank(mobile)){
                    List<Long> ids = dao.mobile("%"+mobile+"%");
                    this.in(ids,criteriaBuilder,root,list);
                }
                if(StringUtils.isNotBlank(teamName)){
                    List<Long> ids = dao.teamName("%"+teamName+"%");
                    this.in(ids,criteriaBuilder,root,list);
                }
                Task task = taskDao.findById(taskJoin.getTaskId());
                if(StringUtils.isNotBlank(nickName)){
                    if(task.getJoinType()==0){
                        List<Long> ids = dao.nickName("%"+nickName+"%");
                        this.in(ids,criteriaBuilder,root,list);
                    }else {
                        List<Long> ids = dao.teamNickName("%"+nickName+"%");
                        this.in(ids,criteriaBuilder,root,list);
                    }
                }
                if(taskJoin.getStatus()!=null){
                    list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), taskJoin.getStatus()));
                }

                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }

            private void in(List<Long> ids, CriteriaBuilder criteriaBuilder, Root<TaskJoin> root, List<Predicate> list) {
                if(ids.size()>0 && !ids.isEmpty()) {
                    Iterator iterator = ids.iterator();
                    CriteriaBuilder.In in = criteriaBuilder.in(root.get("joinId"));
                    while (iterator.hasNext()) {
                        in.value(iterator.next());
                    }
                    list.add(in);
                }else {
                    list.add(criteriaBuilder.equal(root.get("joinId").as(Long.class), 0));
                }
            }
        };
        return dao.findAll(spec, new PageRequest(currnetPage - 1, pagesize, Sort.Direction.DESC,"id"));
    }

}
