package com.example.yong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"user.*"})
@EnableJpaRepositories(basePackages = {"user.repository"})
@EntityScan(basePackages = {"user.entity"})
public class YongApplication {

    public static void main(String[] args) {
        SpringApplication.run(YongApplication.class, args);
    }

}
