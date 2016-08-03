package com.leoman.record.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.record.dao.IntegralRecordDao;
import com.leoman.record.entity.IntegralRecord;
import com.leoman.record.service.IntegralRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Created by Administrator on 2016/8/3.
 */
@Service
public class IntegralRecordServiceImpl extends GenericManagerImpl<IntegralRecord,IntegralRecordDao> implements IntegralRecordService{

    @Autowired
    private IntegralRecordDao dao;


}
