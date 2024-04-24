package com.example.hackathon4.google;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleTranslate {
    public String translate(String text, String language1,String language2){

         System.out.println(text);
        System.out.println(language1);

        System.out.println(language2);


        try {
            // Замените YOUR_API_KEY на ваш API-ключ
            String apiKey = "AIzaSyAPXbaIlXvuDRCDtnPdh0lh83bo0zW0fps";
            // Замените YOUR_TEXT_TO_TRANSLATE на текст, который вы хотите перевести
            String textToTranslate = text;
            // Замените YOUR_SOURCE_LANG и YOUR_TARGET_LANG на исходный и целевой языки соответственно
            String sourceLang = language1;
            String targetLang = language2;

            // Формирование URL для запроса
            String urlStr = "https://translation.googleapis.com/language/translate/v2?key=" + apiKey +
                    "&source=" + sourceLang +
                    "&target=" + targetLang +
                    "&q=" + URLEncoder.encode(textToTranslate, "UTF-8");

            // Создание объекта URL
            URL url = new URL(urlStr);

            // Создание объекта HttpURLConnection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Установка метода запроса GET
            connection.setRequestMethod("GET");

            // Получение ответа от сервера
            int responseCode = connection.getResponseCode();

            // Проверка успешности запроса
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Чтение ответа от сервера
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // Закрытие потока чтения
                in.close();

                // Преобразование ответа в объект JSON
                JSONObject jsonResponse = new JSONObject(response.toString());

                // Получение переведенного текста из объекта JSON
               String translatedText = jsonResponse.getJSONObject("data")
                        .getJSONArray("translations")
                        .getJSONObject(0)
                        .getString("translatedText");
               System.out.println(translatedText);

               return translatedText;

            } else {
                System.out.println("GET request failed: " + responseCode);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        System.out.println(text);
        return null ;
    }

}
