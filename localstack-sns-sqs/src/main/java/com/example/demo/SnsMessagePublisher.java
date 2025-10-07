package com.example.demo;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.example.demo.config.AwsConfigProperties;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SnsMessagePublisher {

    private final AwsConfigProperties awsConfigProperties;
    private final SnsTemplate snsTemplate;
    private final AmazonSNS amazonSNS;

    public void publishMessage(String message) {
        publishMessage(message, null);
    }

    public void publishMessage(String message, String subject) {
        final var topicName = awsConfigProperties.sns().topicName();
        final var topicArn = awsConfigProperties.sns().topicArn();

        log.info("Publishing message to topic {}: {}", topicName, message);
        snsTemplate.sendNotification(topicArn, message, subject);
    }

    public void publishMessageWithAttributes(String message, Map<String, String> attributes) {
        final var topicName = awsConfigProperties.sns().topicName();
        final var topicArn = awsConfigProperties.sns().topicArn();
        log.info("Publishing message with attributes to topic {}: {}", topicName, message);

        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            messageAttributes.put(entry.getKey(), new MessageAttributeValue()
                    .withDataType("String")
                    .withStringValue(entry.getValue()));
        }
        var publishRequest = new PublishRequest()
                .withTopicArn(topicArn)
                .withMessage(message)
                .withMessageAttributes(messageAttributes);

        amazonSNS.publish(publishRequest);
    }
}