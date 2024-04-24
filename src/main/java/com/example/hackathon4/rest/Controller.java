package com.example.hackathon4.rest;


import com.example.hackathon4.basic.Basic;
import com.example.hackathon4.chatGPT.ChatGPT;
import com.example.hackathon4.data.Data;
import com.example.hackathon4.database.PhotoDatabase;
import com.example.hackathon4.google.Tts;
import com.example.hackathon4.photo.Photo;
import com.example.hackathon4.video.AudioUploader;
import com.example.hackathon4.video.Video;
import com.example.hackathon4.video.VideoSave;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api")
public class Controller {

    Video video=new Video();
    Basic basic = new Basic();
    PhotoDatabase photo = new PhotoDatabase();

    Tts tts=new Tts();
    ChatGPT ch=new ChatGPT();
    Photo photo1=new Photo();
    VideoSave save=new VideoSave();
    AudioUploader audioUploader=new AudioUploader();
    @PostMapping("/example")
    public ResponseEntity<Resource> postExample(@RequestBody Data requestBody) throws IOException, InterruptedException, ParseException {
        String name = requestBody.getName();
        String llong = requestBody.getLlong();
        String language = requestBody.getLanguage();
        String size = requestBody.getSize();

        System.out.println("Received request with parameters:");
        System.out.println("name: " + name);
        System.out.println("llong: " + llong);
        System.out.println("language: " + language);
        System.out.println("size: " + size);




       //   System.out.println(audioUploader.upload("C:/ai_hackathon/hackathon4/output.mp3"));

        String answer=basic.basick(name,llong,language,size);

        System.out.println(answer);
        // Замените путь к видеофайлу на ваш реальный путь

        String answer2="C/ai_hackathon/hackathon4/photo_save2/"+answer;

        File videoFile = new File(answer);

        // Проверяем, существует ли файл
        if (!videoFile.exists() || !videoFile.isFile()) {
            return ResponseEntity.notFound().build();
        }

        try {
            // Получаем контент тип из mime типа файла
            MediaType mediaType = MediaType.parseMediaType(Files.probeContentType(videoFile.toPath()));

            // Устанавливаем заголовки для видеофайла
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(mediaType);

            // Указываем Content-Disposition как inline, чтобы видео открылось в браузере, а не скачивалось
            headers.setContentDispositionFormData("inline", videoFile.getName());

            // Возвращаем видеофайл в теле ответа
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new FileSystemResource(videoFile));
        } catch (IOException e) {
            // Ошибка чтения файла
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}