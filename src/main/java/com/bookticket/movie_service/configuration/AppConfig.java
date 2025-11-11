package com.bookticket.movie_service.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {

    @Bean("theaterApiClient")
    @LoadBalanced
    public RestClient theaterRestClient() {
        return RestClient.create("lb://theater-service");
    }
}
