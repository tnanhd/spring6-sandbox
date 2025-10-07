package com.example.demo.client;

import com.example.demo.model.AuthRequest;
import com.example.demo.model.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "dummyJsonAuthClient", url = "${feign.client.url}")
public interface DummyJsonAuthClient {

    @PostMapping("/login")
    AuthResponse login(AuthRequest request);
}
