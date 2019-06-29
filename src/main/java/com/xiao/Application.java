package com.xiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling//开启定时任务
//引导类
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
