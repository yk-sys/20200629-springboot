package com.aaa.yk.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Param
 * @ClassName ApplicationRun
 * @Description TODO
 * @Author yk
 * @Date 2020/6/29 0029 14:33
 * @Return
 **/
@SpringBootApplication
@MapperScan("com.aaa.yk.springboot.mapper")
public class ApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class,args);
    }
}
