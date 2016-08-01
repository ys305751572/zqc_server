package com.leoman.task.service;

import com.leoman.common.service.GenericManager;
import com.leoman.task.entity.Task;
import org.springframework.data.domain.Page;

/**
 * 任务
 * Created by Administrator on 2016/7/28.
 */
public interface TaskService extends GenericManager<Task>{

    public Page<Task> findPage(Task task,Integer taskStatus,Integer checkpointStatus,int currnetPage,int pagesize);

}
