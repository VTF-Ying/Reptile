package com.reptile;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 173415
 */
@SpringBootApplication
@MapperScan("com.reptile.mapper")
public class ReptileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReptileApplication.class, args);
    }

}
