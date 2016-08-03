package com.leoman.report.service;

import com.leoman.common.service.GenericManager;
import com.leoman.report.entity.Report;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/8/3.
 */
public interface ReportService extends GenericManager<Report>{

    public Page<Report> findPage(Report report,int currnetPage, int pagesize);

}
