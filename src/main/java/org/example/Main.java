package org.example;

import org.example.repository.WorkoutRecordFileRepository;
import org.example.repository.WorkoutRecordRepository;
import org.example.service.WorkoutRecordService;
import org.example.view.ConsoleView;

import org.example.util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
//        WorkoutRecordRepository repository = new WorkoutRecordFileRepository();
//        WorkoutRecordService service = new WorkoutRecordService(repository);
//        ConsoleView view = new ConsoleView(service);
//
//        view.run();

        try (Connection connection = DBConnection.getInstance().getConnection()) {
            System.out.println("DB 연결 성공!");
            System.out.println("현재 DB: " + connection.getCatalog());
        } catch (SQLException e) {
            System.out.println("DB 연결 실패");
            e.printStackTrace();
        }
    }
}