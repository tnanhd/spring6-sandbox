package com.example.demo.client;

import com.example.demo.model.PostResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "dummyJsonClient", url = "${feign.client.url}")
public interface DummyJsonClient {

    @GetMapping("/posts")
    PostResponse getPosts();
}
