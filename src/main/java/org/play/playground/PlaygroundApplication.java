package org.play.playground;

import org.play.playground.services.SimpleRetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableRetry
public class PlaygroundApplication {
    @Autowired
    private SimpleRetryService simpleRetryService;

    public static void main(String[] args) {
        SpringApplication.run(PlaygroundApplication.class, args);
    }

    @PostConstruct
    public void init() throws Exception {
        simpleRetryService.download();
    }
}
