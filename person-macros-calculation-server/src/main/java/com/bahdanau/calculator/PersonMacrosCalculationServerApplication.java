package com.bahdanau.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PersonMacrosCalculationServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonMacrosCalculationServerApplication.class, args);
    }
}
