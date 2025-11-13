package com.stageon.stageonwas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class StageonWasApplication {

    public static void main(String[] args) {
        SpringApplication.run(StageonWasApplication.class, args);
    }

}
