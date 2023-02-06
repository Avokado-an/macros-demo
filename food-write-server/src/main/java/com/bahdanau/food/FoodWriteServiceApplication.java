package com.bahdanau.food;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

// All food details are specified as 100g portions, keep it in mind while calculating
@SpringBootApplication
@EnableDiscoveryClient
public class FoodWriteServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodWriteServiceApplication.class, args);
    }
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
