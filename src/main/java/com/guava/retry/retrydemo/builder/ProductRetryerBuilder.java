package com.guava.retry.retrydemo.builder;

import com.github.rholder.retry.*;
import com.guava.retry.retrydemo.exception.IsNeedRetryException;
import com.guava.retry.retrydemo.listener.RetryLogListener;
import com.guava.retry.retrydemo.util.SpinBlockStrategy;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author vangoleo
 * @create 2020-04-27 3:13 下午
 * @desc 构造重试器
 **/
@Component
public class ProductRetryerBuilder {

    /**
     * 构造重试器
     *
     * @return
     */
    public Retryer build() {

        return RetryerBuilder.<Boolean>newBuilder()
                .retryIfExceptionOfType(IsNeedRetryException.class)
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(2, TimeUnit.SECONDS))
                .withBlockStrategy(new SpinBlockStrategy())
                .withRetryListener(new RetryLogListener())
                .build();
    }

}