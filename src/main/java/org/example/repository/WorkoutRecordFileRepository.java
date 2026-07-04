package org.example.repository;

import org.example.model.WorkoutRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WorkoutRecordFileRepository implements WorkoutRecordRepository {

    private final Path filePath = Path.of("data", "record.txt");

    public WorkoutRecordFileRepository() {
        try {
            Files.createDirectories(filePath.getParent());

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 저장소 초기화 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public WorkoutRecord save(WorkoutRecord record) {
        List<WorkoutRecord> records = findAll();

        long maxId = 0L;

        for (WorkoutRecord existingRecord : records) {
            if (existingRecord.getId() > maxId) {
                maxId = existingRecord.getId();
            }
        }

        long nextId = maxId + 1;

        record.setId(nextId);

        String line = toLine(record);

        try {
            Files.writeString(
                    filePath,
                    line + System.lineSeparator(),
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new RuntimeException("운동 기록 저장 중 오류가 발생했습니다.", e);
        }

        return record;
    }

    @Override
    public List<WorkoutRecord> findAll() {
        try {
            List<String> lines = Files.readAllLines(filePath);
            List<WorkoutRecord> records = new ArrayList<>();

            for (String line : lines) {
                if (!line.isBlank()) {
                    records.add(fromLine(line));
                }
            }

            return records;
        } catch (IOException e) {
            throw new RuntimeException("운동 기록 조회 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public Optional<WorkoutRecord> findById(Long id) {
        return findAll().stream()
                .filter(record -> record.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean update(WorkoutRecord record) {
        List<WorkoutRecord> records = findAll();
        boolean updated = false;

        for (int i = 0; i < records.size(); i++) {
            WorkoutRecord existingRecord = records.get(i);

            if (existingRecord.getId().equals(record.getId())) {
                records.set(i, record);
                updated = true;
                break;
            }
        }

        if (updated) {
            writeAll(records);
        }

        return updated;
    }

    private void writeAll(List<WorkoutRecord> records) {
    }


    @Override
    public boolean deleteById(Long id) {
        List<WorkoutRecord> records = findAll();
        boolean deleted = false;

        List<WorkoutRecord> remainingRecords = new ArrayList<>();

        for (WorkoutRecord record : records) {
            if (record.getId().equals(id)) {
                deleted = true;
            } else {
                remainingRecords.add(record);
            }
        }

        if (deleted) {
            writeAll(remainingRecords);
        }

        return deleted;
    }

    private String toLine(WorkoutRecord record) {
        return record.getId() + "|" +
                record.getExerciseName() + "|" +
                record.getWeight() + "|" +
                record.getReps() + "|" +
                record.getSets() + "|" +
                record.getWorkoutDate() + "|" +
                record.getMemo();
    }

    private WorkoutRecord fromLine(String line) {
        String[] parts = line.split("\\|");

        return new WorkoutRecord(
                Long.parseLong(parts[0]),
                parts[1],
                Double.parseDouble(parts[2]),
                Integer.parseInt(parts[3]),
                Integer.parseInt(parts[4]),
                LocalDate.parse(parts[5]),
                parts[6]
        );
    }
}