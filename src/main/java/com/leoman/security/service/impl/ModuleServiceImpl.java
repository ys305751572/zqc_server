package com.leoman.security.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.security.dao.ModuleDao;
import com.leoman.security.entity.Module;
import com.leoman.security.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yesong on 2016/5/22.
 */
@Service
public class ModuleServiceImpl extends GenericManagerImpl<Module,ModuleDao> implements ModuleService {

    @Autowired
    private ModuleDao dao;

    @Override
    public Page<Module> findPage(Integer pagenum, Integer pagesize) {
        Specification<Module> spec = new Specification<Module>() {
            @Override
            public Predicate toPredicate(Root<Module> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec,new PageRequest(pagenum-1,pagesize, Sort.Direction.DESC,"id"));
    }

    @Override
    public void saveModule(Module module, Long parentId) {
        if(parentId != null) {
            // add child module
            saveParentModule(module);
        }
        else {
            // add parent module
            saveChildModule(module, parentId);
        }
        System.out.println("=====saveModule()=====");
    }

    private void saveParentModule(Module module) {
        this.save(module);
    }

    public void saveChildModule(Module module,Long parentId) {
        Module parentModule = this.queryByPK(parentId);
        module.getChilds().add(module);
        module.setParent(parentModule);
        this.save(module);
    }
}
