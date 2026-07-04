package org.example;

import org.example.model.WorkoutRecord;
import org.example.repository.WorkoutRecordFileRepository;
import org.example.repository.WorkoutRecordRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        WorkoutRecordRepository repository = new WorkoutRecordFileRepository();

        System.out.println("===== FileWorkoutRecordRepository 전체 테스트 시작 =====");

        // 1. 저장 테스트
        WorkoutRecord record1 = new WorkoutRecord(
                "Bench Press",
                60.0,
                10,
                3,
                LocalDate.of(2026, 7, 4),
                "컨디션 좋음"
        );

        WorkoutRecord record2 = new WorkoutRecord(
                "Squat",
                80.0,
                8,
                4,
                LocalDate.of(2026, 7, 4),
                "하체 운동"
        );

        WorkoutRecord savedRecord1 = repository.save(record1);
        WorkoutRecord savedRecord2 = repository.save(record2);

        System.out.println("\n[1] 저장 테스트");
        System.out.println("저장된 기록 1: " + savedRecord1);
        System.out.println("저장된 기록 2: " + savedRecord2);

        // 2. 전체 조회 테스트
        System.out.println("\n[2] 전체 조회 테스트");
        List<WorkoutRecord> allRecords = repository.findAll();

        for (WorkoutRecord record : allRecords) {
            System.out.println(record);
        }

        // 3. id로 조회 테스트
        System.out.println("\n[3] id로 조회 테스트");
        Optional<WorkoutRecord> foundRecord = repository.findById(savedRecord1.getId());

        if (foundRecord.isPresent()) {
            System.out.println("찾은 기록: " + foundRecord.get());
        } else {
            System.out.println("해당 id의 기록을 찾지 못했습니다.");
        }

        // 4. 수정 테스트
        System.out.println("\n[4] 수정 테스트");

        WorkoutRecord updatedRecord = new WorkoutRecord(
                savedRecord1.getId(),
                "Bench Press",
                65.0,
                8,
                4,
                LocalDate.of(2026, 7, 4),
                "무게 증가"
        );

        boolean updateResult = repository.update(updatedRecord);

        System.out.println("수정 성공 여부: " + updateResult);
        System.out.println("수정 후 전체 기록:");

        for (WorkoutRecord record : repository.findAll()) {
            System.out.println(record);
        }

        // 5. 삭제 테스트
        System.out.println("\n[5] 삭제 테스트");

        boolean deleteResult = repository.deleteById(savedRecord2.getId());

        System.out.println("삭제 성공 여부: " + deleteResult);
        System.out.println("삭제 후 전체 기록:");

        for (WorkoutRecord record : repository.findAll()) {
            System.out.println(record);
        }

        // 6. 없는 id 조회 테스트
        System.out.println("\n[6] 없는 id 조회 테스트");

        Optional<WorkoutRecord> notFoundRecord = repository.findById(999L);

        if (notFoundRecord.isPresent()) {
            System.out.println("찾은 기록: " + notFoundRecord.get());
        } else {
            System.out.println("id 999 기록은 없습니다.");
        }

        // 7. 없는 id 삭제 테스트
        System.out.println("\n[7] 없는 id 삭제 테스트");

        boolean deleteFailResult = repository.deleteById(999L);

        System.out.println("없는 id 삭제 성공 여부: " + deleteFailResult);

        System.out.println("\n===== 전체 테스트 종료 =====");
    }
}

