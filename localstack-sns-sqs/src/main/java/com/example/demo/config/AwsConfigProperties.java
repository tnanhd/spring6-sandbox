package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.example.aws")
public record AwsConfigProperties(Sqs sqs, Sns sns) {
    public record Sqs(String queueName) { }
    public record Sns(
            String topicName,
            String topicArn,
            String endpoint,
            String region
    ) { }
}
