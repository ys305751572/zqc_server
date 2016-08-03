package com.leoman.product.service;

import com.leoman.common.service.GenericManager;
import com.leoman.product.entity.Product;
import com.leoman.product.entity.ProductExchangeRecord;
import org.springframework.data.domain.Page;

/**
 *
 * Created by Administrator on 2016/8/3.
 */
public interface ProductExchangeRecordService extends GenericManager<ProductExchangeRecord>{

    public Page<ProductExchangeRecord> findPage(ProductExchangeRecord productExchangeRecord, int currnetPage, int pagesize);

}
