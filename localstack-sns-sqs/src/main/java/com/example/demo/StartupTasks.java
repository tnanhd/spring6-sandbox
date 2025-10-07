package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("!test")
public class StartupTasks implements CommandLineRunner {

    private final SnsMessagePublisher snsMessagePublisher;

    @Override
    public void run(String... args) throws Exception {
        log.info("Application started. Publishing a test message to SNS...");
        snsMessagePublisher.publishMessage("Hello from SNS! Timestamp: " + System.currentTimeMillis());
    }
}
