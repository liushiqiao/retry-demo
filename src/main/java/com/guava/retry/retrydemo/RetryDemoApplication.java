package com.guava.retry.retrydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RetryDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetryDemoApplication.class, args);
    }

}
