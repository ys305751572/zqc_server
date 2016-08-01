package com.leoman.product.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.product.dao.ProductDao;
import com.leoman.product.entity.Product;
import com.leoman.product.service.ProductService;
import com.leoman.task.entity.Task;
import org.apache.commons.lang.StringUtils;
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
 *
 * Created by Administrator on 2016/8/1.
 */
@Service
public class ProductServiceImpl extends GenericManagerImpl<Product,ProductDao> implements ProductService{

    @Autowired
    private ProductDao dao;

    @Override
    public Page<Product> findPage(final Product product, int currnetPage, int pagesize) {
        Specification<Product> spec = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(product.getName())){
                    list.add(criteriaBuilder.like(root.get("name").as(String.class), "%"+product.getName()+"%"));
                }
                if(product.getType()!=null){
                    list.add(criteriaBuilder.equal(root.get("type").as(Integer.class), product.getType()));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec, new PageRequest(currnetPage - 1, pagesize, Sort.Direction.DESC,"id"));
    }
}
