package com.leoman.content.service;

import com.leoman.common.service.GenericManager;
import com.leoman.content.entity.Banner;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/7/28.
 */
public interface BannerService extends GenericManager<Banner> {

    public Page<Banner> findPage(Banner banner, int currentPage, int pagesize);
}
