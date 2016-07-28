package com.leoman.content.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.content.dao.BannerDao;
import com.leoman.content.entity.Banner;
import com.leoman.content.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


/**
 * Created by Administrator on 2016/7/28.
 */
@Service
public class BannerServiecImpl extends GenericManagerImpl<Banner, BannerDao> implements BannerService {

    @Autowired
    private BannerDao dao;

    @Override
    public Page<Banner> findPage(final Banner banner, int currentPage, int pagesize) {
        return dao.findAll(new PageRequest(currentPage - 1, pagesize, Sort.Direction.DESC, "id"));
    }
}
