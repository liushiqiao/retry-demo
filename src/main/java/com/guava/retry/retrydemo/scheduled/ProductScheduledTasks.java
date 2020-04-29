package com.guava.retry.retrydemo.scheduled;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.guava.retry.retrydemo.builder.ProductRetryerBuilder;
import com.guava.retry.retrydemo.service.ProductInformationHander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

/**
 * @author vangoleo
 * @create 2020-04-27 3:18 下午
 * @desc 商品定时器
 **/
@Component
public class ProductScheduledTasks {

    @Autowired
    private ProductRetryerBuilder builder;

    @Autowired
    private ProductInformationHander hander;

    @Scheduled(fixedDelay = 30 * 1000)
    public void syncPrice() throws ExecutionException, RetryException {

        Retryer retryer = builder.build();
        retryer.call(hander);
    }
}