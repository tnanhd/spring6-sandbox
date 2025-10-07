package com.example.demo;

//import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SnsNotificationMessage;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SqsMessageListener {

//    private final ObjectMapper objectMapper;

//    @SqsListener(value = "${aws.sqs.queue-name-with-filter:my-test-queue-with-filter}")
//    public void listenDeserializeManuallyFilterTopic(String message) {
//        log.info("Received message from SQS: {}", message);
//        try {
//            SnsNotification snsNotification = objectMapper.readValue(message, SnsNotification.class);
//            log.info("Parsed SNS Notification: MessageId={}, Message={}",
//                    snsNotification.getMessageId(),
//                    snsNotification.getMessage());
//        } catch (Exception e) {
//            log.error("Failed to parse SNS notification", e);
//        }
//    }
//
//    @SqsListener(value = "${aws.sqs.queue-name-1:my-test-queue-1}")
//    public void listenThenDeserializeAutomatic(SnsNotification message) {
//        log.info("Received message from SQS: {}", message.getMessage());
//    }

    @SqsListener(value = "${aws.sqs.queue-name:my-test-queue}", acknowledgementMode = "ON_SUCCESS")
    public void listenNormalQueue(@SnsNotificationMessage String message) {
        log.info("Received normal message from SQS: {}", message);
    }

    @SqsListener(value = "${aws.sqs.filtered-queue-name:my-filtered-queue}", acknowledgementMode = "ON_SUCCESS")
    public void listenFilteredQueue(@SnsNotificationMessage String message) {
        log.info("Received filtered message from SQS: {}", message);
    }
}
