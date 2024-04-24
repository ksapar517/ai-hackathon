package com.example.hackathon4.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PhotoDatabase {

    // Параметры подключения к базе данных PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/ai_hackathon";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "5566";

    // Метод для вставки фотографии в базу данных
    public static void insertPhoto(String filepath, String url) {
        String sql = "INSERT INTO photos (filepath, url) VALUES (?,? )";

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, filepath);
            pstmt.setString(2, url);
            pstmt.executeUpdate();
            System.out.println("Photo inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting photo: " + e.getMessage());
        }
    }


}
