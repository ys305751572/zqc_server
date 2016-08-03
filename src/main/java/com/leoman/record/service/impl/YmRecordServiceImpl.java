package com.leoman.record.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.record.dao.YmRecordDao;
import com.leoman.record.entity.YmRecord;
import com.leoman.record.service.YmRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Created by Administrator on 2016/8/3.
 */
@Service
public class YmRecordServiceImpl extends GenericManagerImpl<YmRecord,YmRecordDao> implements YmRecordService {

    @Autowired
    private YmRecordDao dao;


}
