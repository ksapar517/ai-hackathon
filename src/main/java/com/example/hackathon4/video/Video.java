package com.example.hackathon4.video;

import com.example.hackathon4.chatGPT.ChatGPT;
import com.example.hackathon4.photo.Photo;
import com.example.hackathon4.photo.Photo1;
import org.apache.tomcat.util.json.ParseException;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Video{
    String bb;
    ChatGPT ch=new ChatGPT();
    AudioUploader audioUploader=new AudioUploader();

    VideoSave vs=new VideoSave();
Photo p=new Photo();
Photo1 p1=new Photo1();
    public  String saveVideo(String aa,String name,String llong) throws IOException, InterruptedException, ParseException {


        String url = "https://api.creatomate.com/v1/renders";
        String token = "0124918422a044928575ec98d0ef8610bb4094e35d625da88d1832a8c1c08dcd4e5aabf4a942f58053e9b4dc96759fa4";

        String audioFilePath = "C:/ai_hackathon/hackathon4/output.mp3";

        // Чтение содержимого аудиофайла в виде массива байтов
        byte[] audioBytes = Files.readAllBytes(Paths.get(audioFilePath));

        // Преобразование массива байтов в строку Base64
        String base64Audio = java.util.Base64.getEncoder().encodeToString(audioBytes);

        // JSON-данные для отправки
        String a1 = audioUploader.upload("C:/ai_hackathon/hackathon4/output.mp3");
        String a2 = p.save(name);
        String a3 = p1.save1(name);
        String a4 = p.save(name);
        String a5 = p1.save1(name);

        String data = "{\"template_id\":\"fc8c4d29-8cb5-4649-be36-f1b297a8d979\",\"modifications\":{\"Music\":\"" + a1 + "\"," +
                "\"Background-1\":\"" + a5 + "\"," +
                "\"Text-1\":\"🔥\"," +
                "\"Background-2\":\"" + a2 + "\"," +
                "\"Text-2\":\"😊\"," +
                "\"Background-3\":\"" + a3 + "\"," +
                "\"Text-3\":\".\"," +
                "\"Background-4\":\"" + a4 + "\"," +
                "\"Text-4\":\" \"}}";





        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        try {
            HttpResponse<byte[]> response1 = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
            byte[] videoData = response1.body();

            String jsonResponse = new String(videoData);

            // Вывод данных видео в консоль (если необходимо)
            System.out.println("Video Data:");

            // Вывод URL запроса и JSON-ответа в консоль
            System.out.println(new StringBuilder().append("Request URL: ").append(url).toString());
            System.out.println("JSON Response:");
            System.out.println();

            int startIndex = jsonResponse.indexOf("\"url\":\"") + "\"url\":\"".length();


            int endIndex = jsonResponse.indexOf("\"", startIndex);


            String url1 = jsonResponse.substring(startIndex, endIndex);


            System.out.println("URL from JSON Response:");
            System.out.println(url1);



            Path videoFilePath = Path.of("C:\\ai_hackathon\\hackathon4\\photo_save\\video.mp4");

            // Сохраняем данные видео в файл
            Files.write(videoFilePath, videoData);

            File videoFile = videoFilePath.toFile();


            String requestUrl = "https://api.creatomate.com/v1/renders";

            System.out.println(Files.write(videoFilePath, videoData));

           // String answer=vs.video();

            bb = vs.video(url1);

             System.out.println(bb);
             return bb;

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return bb;

    }
}
