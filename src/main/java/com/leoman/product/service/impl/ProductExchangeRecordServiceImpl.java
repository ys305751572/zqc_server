package com.leoman.product.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.product.dao.ProductExchangeRecordDao;
import com.leoman.product.entity.Product;
import com.leoman.product.entity.ProductExchangeRecord;
import com.leoman.product.service.ProductExchangeRecordService;
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
 * Created by Administrator on 2016/8/3.
 */
@Service
public class ProductExchangeRecordServiceImpl extends GenericManagerImpl<ProductExchangeRecord,ProductExchangeRecordDao> implements ProductExchangeRecordService{

    @Autowired
    private ProductExchangeRecordDao dao;

    @Override
    public Page<ProductExchangeRecord> findPage(final ProductExchangeRecord productExchangeRecord, int currnetPage, int pagesize) {
        Specification<ProductExchangeRecord> spec = new Specification<ProductExchangeRecord>() {
            @Override
            public Predicate toPredicate(Root<ProductExchangeRecord> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();

                if(StringUtils.isNotBlank(productExchangeRecord.getName())){
                    list.add(criteriaBuilder.like(root.get("name").as(String.class),"%"+productExchangeRecord.getName()+"%"));
                }

                if(StringUtils.isNotBlank(productExchangeRecord.getNickname())){
                    list.add(criteriaBuilder.like(root.get("nickname").as(String.class),"%"+productExchangeRecord.getNickname()+"%"));
                }

                if(StringUtils.isNotBlank(productExchangeRecord.getProductName())){
                    list.add(criteriaBuilder.like(root.get("productName").as(String.class),"%"+productExchangeRecord.getProductName()+"%"));
                }

                if(productExchangeRecord.getProductType()!=null){
                    list.add(criteriaBuilder.equal(root.get("productType").as(Integer.class),productExchangeRecord.getProductType()));
                }

                if(productExchangeRecord.getStatus()!=null){
                    list.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("status").as(Integer.class),productExchangeRecord.getStatus()),criteriaBuilder.notEqual(root.get("productType").as(Integer.class),2)));
                }


                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec, new PageRequest(currnetPage - 1, pagesize, Sort.Direction.DESC,"id"));
    }
}
