package com.example.demo;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.time.Duration;
import java.util.Map;

import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class SnsSqsIntegrationTest {

    @Autowired
    private SnsMessagePublisher messagePublisher;

    @MockitoSpyBean
    private SqsMessageListener messageListener;

    @Test
    void testSnsToSqsFlowWithCorrectAttribute() {
        String testMessage = "Hello, this is a test message with correct attribute!";
        messagePublisher.publishMessageWithAttributes(
                testMessage,
                Map.of("target-filtered", "true")
        );

        // Wait up to 2 seconds for the listener to be invoked
        Awaitility.await()
                .atMost(Duration.ofSeconds(10))
                .untilAsserted(() -> {
                            verify(messageListener, timeout(5000)).listenNormalQueue(testMessage);
                            verify(messageListener, timeout(5000)).listenFilteredQueue(testMessage);
                        }
                );
    }

    @Test
    void testSnsToSqsFlowWithWrongAttribute() {
        String testMessage = "Hello, this is a test message with wrong attribute!";
        messagePublisher.publishMessageWithAttributes(
                testMessage,
                Map.of("target-wrong", "true")
        );

        // Wait up to 2 seconds for the listener to be invoked
        Awaitility.await()
                .atMost(Duration.ofSeconds(10))
                .untilAsserted(() -> {
                            verify(messageListener, timeout(5000)).listenNormalQueue(testMessage);
                            verify(messageListener, never()).listenFilteredQueue(testMessage);
                        }
                );
    }

    @AfterEach
    void resetMocks() throws InterruptedException {
        // Reset the spy after each test to avoid interference between tests
        reset(messageListener);
        Thread.sleep(500); // Give some time for any async processing to complete
    }
}
