package org.example.view;

import org.example.model.WorkoutRecord;
import org.example.service.WorkoutRecordService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleView {
    private final WorkoutRecordService service;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleView(WorkoutRecordService service) {
        this.service = service;
    }

    public void run() {
        while (true) {
            printMenu();

            int choice = readInt("메뉴 선택: ");

            switch (choice) {
                case 1:
                    addRecord();
                    break;
                case 2:
                    showAllRecords();
                    break;
                case 3:
                    showRecordById();
                    break;
                case 4:
                    updateRecord();
                    break;
                case 5:
                    deleteRecord();
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 메뉴입니다.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("===== 운동 기록 관리 시스템 =====");
        System.out.println("1. 운동 기록 추가");
        System.out.println("2. 전체 기록 조회");
        System.out.println("3. 운동 기록 상세 조회");
        System.out.println("4. 운동 기록 수정");
        System.out.println("5. 운동 기록 삭제");
        System.out.println("0. 종료");
    }

    private void addRecord() {
        System.out.println("\n[운동 기록 추가]");

        String exerciseName = readString("운동 이름: ");
        double weight = readDouble("무게: ");
        int reps = readInt("반복 횟수: ");
        int sets = readInt("세트 수: ");
        String memo = readString("메모: ");

        WorkoutRecord record = new WorkoutRecord(
                exerciseName,
                weight,
                reps,
                sets,
                LocalDate.now(),
                memo
        );

        try {
            WorkoutRecord savedRecord = service.addRecord(record);
            System.out.println("운동 기록이 저장되었습니다.");
            System.out.println(savedRecord);
        } catch (IllegalArgumentException e) {
            System.out.println("저장 실패: " + e.getMessage());
        }
    }

    private void showAllRecords() {
        System.out.println("\n[전체 운동 기록 조회]");

        List<WorkoutRecord> records = service.getAllRecords();

        if (records.isEmpty()) {
            System.out.println("저장된 운동 기록이 없습니다.");
            return;
        }

        for (WorkoutRecord record : records) {
            System.out.println(record);
        }
    }

    private void showRecordById() {
        System.out.println("\n[운동 기록 상세 조회]");

        Long id = readLong("조회할 id: ");

        Optional<WorkoutRecord> record = service.getRecordById(id);

        if (record.isPresent()) {
            System.out.println(record.get());
        } else {
            System.out.println("해당 id의 운동 기록이 없습니다.");
        }
    }

    private void updateRecord() {
        System.out.println("\n[운동 기록 수정]");

        Long id = readLong("수정할 id: ");

        Optional<WorkoutRecord> foundRecord = service.getRecordById(id);

        if (foundRecord.isEmpty()) {
            System.out.println("해당 id의 운동 기록이 없습니다.");
            return;
        }

        String exerciseName = readString("새 운동 이름: ");
        double weight = readDouble("새 무게: ");
        int reps = readInt("새 반복 횟수: ");
        int sets = readInt("새 세트 수: ");
        String memo = readString("새 메모: ");

        WorkoutRecord updatedRecord = new WorkoutRecord(
                id,
                exerciseName,
                weight,
                reps,
                sets,
                LocalDate.now(),
                memo
        );

        try {
            boolean result = service.updateRecord(updatedRecord);

            if (result) {
                System.out.println("운동 기록이 수정되었습니다.");
            } else {
                System.out.println("수정에 실패했습니다.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("수정 실패: " + e.getMessage());
        }
    }

    private void deleteRecord() {
        System.out.println("\n[운동 기록 삭제]");

        Long id = readLong("삭제할 id: ");

        boolean result = service.deleteRecord(id);

        if (result) {
            System.out.println("운동 기록이 삭제되었습니다.");
        } else {
            System.out.println("해당 id의 운동 기록이 없습니다.");
        }
    }

    private String readString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }

    private long readLong(String message) {
        while (true) {
            try {
                System.out.print(message);
                long value = Long.parseLong(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }

    private double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                double value = Double.parseDouble(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }
}