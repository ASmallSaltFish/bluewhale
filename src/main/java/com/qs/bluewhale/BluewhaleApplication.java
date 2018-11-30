package com.qs.bluewhale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class BluewhaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BluewhaleApplication.class, args);
    }
}
