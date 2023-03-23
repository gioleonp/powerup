package com.pragma.plazoleta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableFeignClients
@EnableEurekaClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class PlazoletaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlazoletaApplication.class, args);
    }
}
