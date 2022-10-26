package com.example.relextesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RelexTestingApplication {
    public static void main(String[] args) {
        SpringApplication.run(RelexTestingApplication.class, args);
    }

}
