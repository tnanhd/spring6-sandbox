package com.example.demo.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Profile({"test", "local"})
@Configuration
public class SnsConfig {

    @Bean
    public AwsClientBuilder.EndpointConfiguration endpointConfigurationLocal(AwsConfigProperties awsConfigProperties) {
        return new AwsClientBuilder.EndpointConfiguration(
                awsConfigProperties.sns().endpoint(),
                awsConfigProperties.sns().region()
        );
    }

    @Bean
    public AWSCredentialsProvider awsCredentialsProviderLocal() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(
                "access-key-id",
                "secret-access"
        ));
    }

    @Bean
    public AmazonSNS amazonSNSLocal(
            AwsClientBuilder.EndpointConfiguration endpointConfigurationLocal,
            AWSCredentialsProvider awsCredentialsProviderLocal
    ) {
        return AmazonSNSClientBuilder
                .standard()
                .withEndpointConfiguration(endpointConfigurationLocal)
                .withCredentials(awsCredentialsProviderLocal)
                .build();
    }

//    @Bean
//    @Profile("prod")
//    public AmazonSNS amazonSNSProd() {
//        return AmazonSNSClientBuilder.standard().build();
//    }
}
