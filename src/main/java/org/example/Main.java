package org.example;

import org.example.model.WorkoutRecord;
import org.example.repository.WorkoutRecordFileRepository;
import org.example.repository.WorkoutRecordRepository;
import org.example.service.WorkoutRecordService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        WorkoutRecordRepository repository = new WorkoutRecordFileRepository();
        WorkoutRecordService service = new WorkoutRecordService(repository);

        WorkoutRecord record = new WorkoutRecord(
                "Bench Press",
                60.0,
                10,
                3,
                LocalDate.now(),
                "컨디션 좋음"
        );

        WorkoutRecord savedRecord = service.addRecord(record);

        System.out.println("저장 완료:");
        System.out.println(savedRecord);

        System.out.println("전체 조회:");
        System.out.println(service.getAllRecords());

        WorkoutRecord updatedRecord = new WorkoutRecord(
                savedRecord.getId(),
                "Bench Press",
                65.0,
                8,
                4,
                LocalDate.now(),
                "무게 증가"
        );

        boolean updateResult = service.updateRecord(updatedRecord);
        System.out.println("수정 성공 여부: " + updateResult);
        System.out.println(service.getAllRecords());

        boolean deleteResult = service.deleteRecord(savedRecord.getId());
        System.out.println("삭제 성공 여부: " + deleteResult);
        System.out.println(service.getAllRecords());
    }
}