package com.guava.retry.retrydemo.entry;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author vangoleo
 * @create 2020-04-27 2:52 下午
 * @desc 商品
 **/
@Data
@AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private  Integer count;
    private BigDecimal price;
}