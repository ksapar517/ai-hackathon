package com.example.hackathon4.google;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Tts {
    int a=0;
    public void tts(String text,String language) {
        try {
            // Ваш ключ API OpenAI
            String apiKey = "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";

            // Текст, который вы хотите преобразовать в речь
           System.out.println(language + "--------------->" + text);

            // Конечная точка API TTS OpenAI
            String apiUrl = "https://api.openai.com/v1/audio/speech";

            // Создание запроса
            String payload = "{\"model\": \"tts-1\", \"voice\": \"alloy\", \"input\": \"" + text + "\", \"language\": \"" + language+ "\"}";

            // Создание URL объекта
            URL url = new URL(apiUrl);

            // Создание объекта соединения
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Установка метода запроса
            connection.setRequestMethod("POST");

            // Установка заголовков запроса
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);

            // Включение потоков для записи и чтения
            connection.setDoOutput(true);
            connection.setDoInput(true);

            // Запись данных запроса в выходной поток
            connection.getOutputStream().write(payload.getBytes("UTF-8"));

            // Получение кода ответа
            int responseCode = connection.getResponseCode();

            // Если код ответа 200, сохраняем аудио в файл
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Чтение аудио-данных из входного потока
                InputStream inputStream = connection.getInputStream();

                // Сохранение аудио в файл
                FileOutputStream fileOutputStream = new FileOutputStream("C:/ai_hackathon/hackathon4/output.mp3");

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                // Закрытие потоков
                inputStream.close();
                fileOutputStream.close();

                System.out.println("Аудио сохранено в файл output.mp3");
            } else {
                System.out.println(a);
                if(a>=5) {
                    // Если код ответа не 200, выводим сообщение об ошибке
                    tts(text, language);
                }
                System.out.println("Ошибка: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}