package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Hello world!
 */
@SpringBootApplication(exclude ={DataSourceAutoConfiguration.class})
@EnableSchemaRegistryClient
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

    }
}
