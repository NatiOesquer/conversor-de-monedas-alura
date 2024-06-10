package com.nataliaoesquer.conversordemoneda;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
public class ApiQuery {
    private static final String API_KEY = "e191f46b931125c3ba98e860";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";

    public ExchangeRates getExchangeRates() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();
        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();


            // Parse JSON manually to ensure the structure is correct
            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
            JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
            Map<String, Double> ratesMap = new Gson().fromJson(conversionRates, Map.class);

            return new ExchangeRates(ratesMap);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException("Failed to fetch exchange rates", e);
        }
    }
}
