package com.atguigu.spzx.manager.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class OrderStatisticsTask {

    @Scheduled(cron = "0/5 * * * * ?")
    public void HelloWorldTask(){
        log.info("HelloWorldTask");
    }
}
