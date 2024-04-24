package com.example.hackathon4.video;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;


import java.io.File;
import java.util.Map;

public class AudioUploader {
    public String upload(String aa) {
        // Замените 'path/to/your/file.mp3' на фактический путь к вашему MP3 файлу
        String filePath = aa;

        String bb = uploadFile(filePath);

        return bb;
    }

    public static String uploadFile(String filePath) {
        try {
            // Проверка существования файла
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File does not exist: " + filePath);
                return null;
            }

            // Конфигурация Cloudinary с вашими учетными данными
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "dcshsquar",
                    "api_key", "735122184572987",
                    "api_secret", "frrYYzsc4GFDF88L9mTFWNuhj9o"));

            // Загрузка MP3 файла на Cloudinary
            Map<?, ?> result = cloudinary.uploader().upload(file, ObjectUtils.asMap(
                    "resource_type", "video", // Используйте "auto" или "video" для MP3 файлов
                    "folder", "mp3_uploads" // Необязательно: укажите имя папки
            ));

            // Вывод URL загруженного MP3 файла
            System.out.println("MP3 file uploaded successfully. URL: " + result.get("secure_url"));

            String bb = (String) result.get("secure_url");

            return bb;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred during the file upload: " + e.getMessage());
        }
        return null;
    }
}
