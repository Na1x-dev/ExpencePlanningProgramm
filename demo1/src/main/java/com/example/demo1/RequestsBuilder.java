package com.example.demo1;

import java.net.URI;
import java.net.http.HttpRequest;

import static com.example.demo1.HelloApplication.SERVER_URL;

public class RequestsBuilder {

    public static HttpRequest postRequest(String requestBody) {
        return HttpRequest.newBuilder()
                .uri(URI.create(SERVER_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
    }

}
