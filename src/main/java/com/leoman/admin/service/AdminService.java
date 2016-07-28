package com.leoman.admin.service;


import com.leoman.admin.entity.Admin;
import com.leoman.common.service.GenericManager;
import com.leoman.common.service.ICommonService;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/3/8.
 */
public interface AdminService extends GenericManager<Admin> {

    public Admin findByUsername(String username);

    public Page<Admin> findAll(Admin admin, Integer currentPage, Integer pageSize) throws Exception;

    public void resetPassword(Long id);

}
