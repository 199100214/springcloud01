package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication//服务的提供者
public class EurekaClientApplication01 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication01.class, args);
    }

}
