package com.example.demo1;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;


public class RequestsBuilder {
    private static HttpClient client = HttpClient.newHttpClient();


    public static String getServerURL() {
        Properties prop = new Properties();
        String fileName = "src/main/resources/app.properties";
        try {
            prop.load(new FileReader(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop.getProperty("server-url");
    }

    public static HttpResponse<String> sendRequest(HttpRequest request) {
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return null;
        }
    }

    public static HttpResponse<String> getRequest(String path) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getServerURL() + path))
                .header("Accept", "application/json")
                .build();
        return RequestsBuilder.sendRequest(request);
    }

    public static HttpResponse<String> getRequestWithProperty(String path, String property) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getServerURL() + path + "/" + property))
                .header("Accept", "application/json")
                .build();
        return RequestsBuilder.sendRequest(request);
    }

    public static HttpResponse<String> postRequest(String requestBody, String path) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getServerURL() + path))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        return RequestsBuilder.sendRequest(request);
    }

    public static HttpResponse<String> putRequest(String requestBody, String path) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getServerURL() + path))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        return RequestsBuilder.sendRequest(request);
    }

    public static HttpResponse<String> deleteRequest(String path) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getServerURL() + path))
                .DELETE()
                .build();
        return RequestsBuilder.sendRequest(request);
    }

}
