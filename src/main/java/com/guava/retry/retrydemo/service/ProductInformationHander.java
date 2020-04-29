package com.guava.retry.retrydemo.service;

import com.guava.retry.retrydemo.dao.ProductRepository;
import com.guava.retry.retrydemo.entry.Product;
import com.guava.retry.retrydemo.exception.IsNeedRetryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author vangoleo
 * @create 2020-04-27 3:03 下午
 * @desc 业务实现
 **/
@Component
@Slf4j
public class ProductInformationHander implements Callable<Boolean> {

    @Autowired
    private ProductRepository productRepository;

    private static Map<Long, BigDecimal> prices=new HashMap<>();

    static {
        prices.put(1L, new BigDecimal(100));
        prices.put(2L, new BigDecimal(200));
        prices.put(3L, new BigDecimal(300));
        prices.put(4L, new BigDecimal(400));
        prices.put(8L, new BigDecimal(800));
        prices.put(9L, new BigDecimal(900));
    }


    @Override
    public Boolean call() throws Exception {

        log.info("sync price begin,prices size={}",prices.size());

        for (Long id : prices.keySet()) {

            Product product = productRepository.findById(id);

            if (null==product){
                throw new IsNeedRetryException("can not find product by id=" + id);
            }
            if (null==product.getCount()||product.getCount()<1){
                throw new IsNeedRetryException("product count is less than 1, id="+id);
            }

            Product updatePriceP = productRepository.updatePrice(id, product.getPrice());
            if (null==updatePriceP){
                return false;
            }

            prices.remove(id);
        }

        log.info("sync price over,prices size={}",prices.size());

        return true;
    }
}