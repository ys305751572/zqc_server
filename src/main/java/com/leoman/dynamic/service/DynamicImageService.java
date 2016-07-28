package com.leoman.dynamic.service;

import com.leoman.common.service.GenericManager;
import com.leoman.dynamic.entity.Dynamic;
import com.leoman.dynamic.entity.DynamicImage;

/**
 * Created by Administrator on 2016/7/28.
 */
public interface DynamicImageService extends GenericManager<DynamicImage>{

    public void updateDynamicId(String imageIds, Long dynamicId);
}
