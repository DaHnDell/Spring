package com.kcanmin.member_post.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@EnableScheduling
@Component
@Log4j2
public class MyTask {
    // @Scheduled(cron = "0 0 5 * * 4")
    @Async
    @Scheduled(cron = "0/5 * * * * *")
    public void printTime(){
        log.info(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(new Date()));
    }

    // 은행 정기점검
    // @Scheduled(cron = "0 0 0 * * *")
    // public void dailyFetch(){
    //     log.info(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(new Date()));
    // }
}


