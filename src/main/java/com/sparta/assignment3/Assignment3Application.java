package com.sparta.assignment3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Assignment3Application {

    public static void main(String[] args) {
        SpringApplication.run(Assignment3Application.class, args);
    }

}
