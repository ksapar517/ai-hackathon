package com.example.hackathon4.photo;

import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Photo {

    public String save(String name) throws IOException, InterruptedException, ParseException {

        String url = "https://api.openai.com/v1/images/generations";
        String apiKey = " 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";

        String jsonBody = """
                {
                    "model": "dall-e-2",
                    "prompt": "%s",
                    "n": %d,
                    "size": "1024x1024"
                }
                """.formatted(name, 1);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();


        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonResponse = new JSONObject(response.body());
        JSONArray dataArray = jsonResponse.getJSONArray("data");

        // Итерация по массиву данных

            JSONObject dataObject = dataArray.getJSONObject(0);
            String url1 = dataObject.getString("url");
            System.out.println("URL " + (0 + 1) + ": " + url1);

        return url1;
        }

    }



