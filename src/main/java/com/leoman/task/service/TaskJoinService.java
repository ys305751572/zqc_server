package com.leoman.task.service;

import com.leoman.common.service.GenericManager;
import com.leoman.task.entity.TaskJoin;
import org.springframework.data.domain.Page;

/**
 *
 * Created by Administrator on 2016/7/29.
 */
public interface TaskJoinService extends GenericManager<TaskJoin>{

    public Page<TaskJoin> findPage(String mobileName,String nickName,TaskJoin taskJoin,int currnetPage,int pagesize);

}
