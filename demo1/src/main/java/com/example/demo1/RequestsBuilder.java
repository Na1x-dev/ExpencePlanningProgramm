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
    public static HttpRequest postRequest(String requestBody, String path) {
        return HttpRequest.newBuilder()
                .uri(URI.create(getServerURL() + path))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
    }

    public static String getServerURL(){
        Properties prop = new Properties();
        String fileName = "src/main/resources/app.properties";
        try {
            prop.load(new FileReader(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop.getProperty("server-url");
    }

    public static HttpResponse<String> sendRequest(String requestBody, String path){
        try {
            return client.send(RequestsBuilder.postRequest(requestBody, path), HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return null;
        }
    }

}
