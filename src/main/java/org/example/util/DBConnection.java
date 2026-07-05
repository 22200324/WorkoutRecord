package org.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static DBConnection instance;

    private final String url;
    private final String username;
    private final String password;
    private final String driver;

    private DBConnection() {
        Properties properties = new Properties();

        try (InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (inputStream == null) {
                throw new RuntimeException("db.properties 파일을 찾을 수 없습니다.");
            }

            properties.load(inputStream);

            this.url = properties.getProperty("db.url");
            this.username = properties.getProperty("db.username");
            this.password = properties.getProperty("db.password");
            this.driver = properties.getProperty("db.driver");

            Class.forName(driver);

        } catch (IOException e) {
            throw new RuntimeException("DB 설정 파일을 읽는 중 오류가 발생했습니다.", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MariaDB 드라이버를 찾을 수 없습니다.", e);
        }
    }

    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }

        return instance;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("DB 연결 중 오류가 발생했습니다.", e);
        }
    }
}