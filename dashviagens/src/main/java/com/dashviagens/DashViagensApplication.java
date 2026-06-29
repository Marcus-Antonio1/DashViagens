package com.dashviagens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DashViagensApplication {

    public static void main(String[] args) {
        SpringApplication.run(DashViagensApplication.class, args);
    }
}
