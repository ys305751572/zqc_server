package com.leoman.security.service;

import com.leoman.common.service.GenericManager;
import com.leoman.security.entity.Module;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/5/22.
 */
public interface ModuleService extends GenericManager<Module>{

    public Page<Module> findPage(Integer pagenum,Integer pagesize);

    public void saveModule(Module module,Long parentId);
}
