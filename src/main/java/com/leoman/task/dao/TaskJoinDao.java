package com.leoman.task.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.task.entity.TaskJoin;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * Created by Administrator on 2016/7/29.
 */
public interface TaskJoinDao extends IBaseJpaRepository<TaskJoin>{

    @Query("SELECT a.id FROM UserInfo a WHERE a.mobile LIKE ?1" )
    public List<Long> mobile(String mobile);

    @Query("SELECT a.id FROM Team a WHERE a.name LIKE ?1" )
    public List<Long> teamName(String teamName);

    @Query("SELECT a.id FROM UserInfo a WHERE a.nickname LIKE ?1" )
    public List<Long> nickName(String nickName);

    @Query("SELECT a.id FROM Team a WHERE a.userinfo.nickname LIKE ?1" )
    public List<Long> teamNickName(String nickName);

}
