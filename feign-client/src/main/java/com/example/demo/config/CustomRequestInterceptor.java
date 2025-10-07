package com.example.demo.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CustomRequestInterceptor implements RequestInterceptor {

    private final List<TargetClientConfigurableRequestInterceptor> interceptors;

    @Override
    public void apply(RequestTemplate template) {
        Class<?> targetFeignClientClass = template.feignTarget().type();
        interceptors.stream()
                .filter(interceptor ->
                        interceptor.getAppliedClass().isAssignableFrom(targetFeignClientClass))
                .findFirst()
                .ifPresent(interceptor -> interceptor.apply(template));
    }
}
