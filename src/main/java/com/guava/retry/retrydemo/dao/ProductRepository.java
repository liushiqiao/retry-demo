package com.guava.retry.retrydemo.dao;

import com.guava.retry.retrydemo.entry.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author vangoleo
 * @create 2020-04-27 2:54 下午
 * @desc
 **/
@Repository
public class ProductRepository {

    private static ConcurrentHashMap<Long, Product> productMap = new ConcurrentHashMap<>();

    private static AtomicLong ids=new AtomicLong(0);

    public List<Product> findAll(){
        return new ArrayList<>(productMap.values());
    }

    public Product findById(Long id){
        return productMap.get(id);
    }

    public Product updatePrice(Long id, BigDecimal price){

        Product product = productMap.get(id);
        if (null!=product){
            product.setPrice(price);
        }
        return product;
    }

    public Product addProduct(Product product){
        long id = ids.addAndGet(1);
        product.setId(id);
        productMap.put(id,product);
        return product;
    }
}