package com.example.demo;

import com.example.demo.client.DummyJsonAuthClient;
import com.example.demo.client.DummyJsonClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartupRunner implements CommandLineRunner {

    private final DummyJsonClient client;
    private final DummyJsonAuthClient authClient;

    @Override
    public void run(String... args) {
        final var postResponse = client.getPosts();
        log.info("Total posts: {}", postResponse.total());
        log.info("Posts retrieved: {}", postResponse.posts().length);
        for (var post : postResponse.posts()) {
            log.info("Post ID: {}, Title: {}, Body: {}", post.id(), post.title(), post.body());
        }

//        final var loginRequest = new AuthRequest(
//                "emilys",
//                "emilyspass"
//        );
//        final var authResponse = authClient.login(loginRequest);
//        log.info("Token: {}", authResponse.accessToken());
    }
}
