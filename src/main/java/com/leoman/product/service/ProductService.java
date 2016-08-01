package com.leoman.product.service;

import com.leoman.common.service.GenericManager;
import com.leoman.product.entity.Product;
import org.springframework.data.domain.Page;

/**
 *
 * Created by Administrator on 2016/8/1.
 */
public interface ProductService extends GenericManager<Product>{

    public Page<Product> findPage(Product product, int currnetPage, int pagesize);

}
