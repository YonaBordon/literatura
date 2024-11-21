package com.aluraChallenge.literatura.service;

import com.aluraChallenge.literatura.exceptions.FetchAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class FetchAPI {

    private final String baseURL;
    private final HttpClient client;

    @Autowired
    public FetchAPI(Environment env) {
        this.baseURL = env.getProperty("baseURL");
        if (baseURL == null || baseURL.isEmpty()) {
            throw new IllegalStateException("La variable de entorno 'baseURL' no está configurada o está vacía.");
        }
        this.client = HttpClient.newHttpClient();
    }

    public String fetchData(String endpoint) {
        if (endpoint == null || endpoint.isEmpty()) {
            throw new IllegalArgumentException("Endpoint cannot be null or empty.");
        }
        String fullURL = baseURL + endpoint;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(fullURL))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new FetchAPIException("Request failed with status code: " + response.statusCode());
            }

            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new FetchAPIException("Error fetching data from endpoint: " + endpoint, e);
        }
    }
}
