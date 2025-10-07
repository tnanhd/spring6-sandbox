package com.example.demo;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class BaseLocalStackTest {

    public static final DockerImageName LOCALSTACK_IMAGE =
            DockerImageName.parse("localstack/localstack:latest");

    @Container
    public static LocalStackContainer localStack =
            new LocalStackContainer(LOCALSTACK_IMAGE)
                    .withServices(LocalStackContainer.Service.SNS, LocalStackContainer.Service.SQS);

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.cloud.aws.sns.endpoint",
                () -> localStack.getEndpointOverride(LocalStackContainer.Service.SNS).toString());
        registry.add("spring.cloud.aws.sqs.endpoint",
                () -> localStack.getEndpointOverride(LocalStackContainer.Service.SQS).toString());
        registry.add("spring.cloud.aws.region", localStack::getRegion);
        registry.add("spring.cloud.aws.credentials.access-key", localStack::getAccessKey);
        registry.add("spring.cloud.aws.credentials.secret-key", localStack::getSecretKey);
    }
}
