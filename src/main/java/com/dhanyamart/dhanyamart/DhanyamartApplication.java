package com.dhanyamart.dhanyamart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(excludeName = {
    "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
})
public class DhanyamartApplication {

    public static void main(String[] args) {
        SpringApplication.run(DhanyamartApplication.class, args);
    }
}