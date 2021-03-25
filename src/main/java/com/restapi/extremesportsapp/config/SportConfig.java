package com.restapi.extremesportsapp.config;

import com.restapi.extremesportsapp.repository.SportRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean
    CommandLineRunner commandLineRunner (SportRepository repository){
        return args -> {
        };

    }
}
