package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.pos"})
public class SalesPointApplication {
    public static void main(String[] args) {
        SpringApplication.run(SalesPointApplication.class, args);
    }
}
