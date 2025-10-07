package com.example.demo.config;

import feign.RequestTemplate;

public interface TargetClientConfigurableRequestInterceptor {

    void apply(RequestTemplate template);

    Class<?> getAppliedClass();
}
