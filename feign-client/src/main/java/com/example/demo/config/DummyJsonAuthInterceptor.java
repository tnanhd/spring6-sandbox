package com.example.demo.config;

import com.example.demo.client.DummyJsonClient;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class DummyJsonAuthInterceptor implements TargetClientConfigurableRequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJlbWlseXMiLCJlbWFpbCI6ImVtaWx5LmpvaG5zb25AeC5kdW1teWpzb24uY29tIiwiZmlyc3ROYW1lIjoiRW1pbHkiLCJsYXN0TmFtZSI6IkpvaG5zb24iLCJnZW5kZXIiOiJmZW1hbGUiLCJpbWFnZSI6Imh0dHBzOi8vZHVtbXlqc29uLmNvbS9pY29uL2VtaWx5cy8xMjgiLCJpYXQiOjE3NTkzMzQ4MzcsImV4cCI6MTc1OTMzODQzN30.7itPDCY_uj2G_as5l-E91vrM4JjqGIHNx-BTlfRcUMU");
    }

    @Override
    public Class<?> getAppliedClass() {
        return DummyJsonClient.class;
    }
}
