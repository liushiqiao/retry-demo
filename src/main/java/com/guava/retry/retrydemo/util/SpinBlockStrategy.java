package com.guava.retry.retrydemo.util;

import com.github.rholder.retry.BlockStrategy;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author lsq
 * @create 2020-04-27 2:35 下午
 * @desc 自旋锁的实现, 不响应线程中断
 **/
@Slf4j
@NoArgsConstructor
public class SpinBlockStrategy implements BlockStrategy {

    @Override
    public void block(long sleepTime) throws InterruptedException {

        LocalDateTime stratTime = LocalDateTime.now();

        long start = System.currentTimeMillis();
        long end=start;
        log.info("[SpinBlockStrategy]...begin wait.");

        while (end-start<=sleepTime){
            end=System.currentTimeMillis();
        }

        Duration duration=Duration.between(stratTime,LocalDateTime.now());

        log.info("[SpinBlockStrategy]...end wait.duration={}", duration.toMillis());
    }
}