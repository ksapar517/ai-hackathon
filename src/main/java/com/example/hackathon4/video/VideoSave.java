package com.example.hackathon4.video;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VideoSave{
    String aa=null;
public int a=0;
    public String video(String videourl) throws InterruptedException {
        System.out.println("Video URL: " + videourl);

        String videoUrl = videourl;
        String fileName = videourl.substring(videourl.lastIndexOf('/') + 1); // Extract file name from URL
        String savePath = "C:\\ai_hackathon\\hackathon4\\photo_save2\\" + fileName; // Adjust the save path and file name as needed

        System.out.println("Save Path: " + savePath);

        try {
            // Open connection
            HttpURLConnection connection = (HttpURLConnection) new URL(videoUrl).openConnection();

            // Set request method
            connection.setRequestMethod("GET");

            // Get response code
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Create directories if they don't exist
                Path directory = Paths.get("C:\\ai_hackathon\\hackathon4\\photo_save2");
                if (!Files.exists(directory)) {
                    Files.createDirectories(directory);
                }

                // Save video to file
                InputStream inputStream = connection.getInputStream();
                FileOutputStream outputStream = new FileOutputStream(savePath);
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
                inputStream.close();

                System.out.println("Video downloaded successfully.");
                System.out.println(fileName);
                aa=savePath;
                return aa;
            } else {
                System.out.println("Failed to download video. Response code: " + responseCode);
                if( a < 10) {
                    try {
                        Thread.sleep(4000); // Ожидание 4 секунды (4000 миллисекунд)
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    a++;
                    video(videourl);
                }
            }
        } catch (Exception e) {




            System.err.println("Error downloading video: " + e.getMessage());
            e.printStackTrace();
        }
        return fileName;
    }
}
