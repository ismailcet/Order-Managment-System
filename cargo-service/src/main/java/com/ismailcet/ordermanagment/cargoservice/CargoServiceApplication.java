package com.ismailcet.ordermanagment.cargoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CargoServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(CargoServiceApplication.class,args);
    }
}
