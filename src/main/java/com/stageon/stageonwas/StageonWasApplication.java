package com.stageon.stageonwas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import jakarta.annotation.PostConstruct;


@EnableScheduling
@EnableAsync
@EnableJpaAuditing
@SpringBootApplication
public class StageonWasApplication {
    private static final Logger log = LoggerFactory.getLogger(StageonWasApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StageonWasApplication.class, args);
    }
    @PostConstruct
    public void deployCheck() {
        log.info("### DEPLOY CHECK: APPLICATION STARTED v1 ###");
    }
}
