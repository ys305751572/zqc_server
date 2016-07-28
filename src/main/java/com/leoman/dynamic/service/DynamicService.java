package com.leoman.dynamic.service;

import com.leoman.common.service.GenericManager;
import com.leoman.dynamic.entity.Dynamic;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/7/28.
 */
public interface DynamicService extends GenericManager<Dynamic> {

    public Page<Dynamic> findPage(Dynamic dynamic, int currentPage, int pagesize);

    public void saveDynamic(Dynamic dynamic,String imageIds);
}
