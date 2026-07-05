package org.example.service;

import org.example.model.WorkoutRecord;
import org.example.repository.WorkoutRecordRepository;

import java.util.List;
import java.util.Optional;

public class WorkoutRecordService {
    private final WorkoutRecordRepository repository;

    public WorkoutRecordService(WorkoutRecordRepository repository) {
        this.repository = repository;
    }

    public WorkoutRecord addRecord(WorkoutRecord record) {
        validateRecord(record);
        return repository.save(record);
    }

    public List<WorkoutRecord> getAllRecords() {
        return repository.findAll();
    }

    public Optional<WorkoutRecord> getRecordById(Long id) {
        return repository.findById(id);
    }

    public boolean updateRecord(WorkoutRecord record) {
        validateRecord(record);

        if (record.getId() == null) {
            throw new IllegalArgumentException("수정할 기록의 id가 필요합니다.");
        }

        return repository.update(record);
    }

    public boolean deleteRecord(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("삭제할 기록의 id가 필요합니다.");
        }

        return repository.deleteById(id);
    }

    private void validateRecord(WorkoutRecord record) {
        if (record == null) {
            throw new IllegalArgumentException("운동 기록이 비어 있습니다.");
        }

        if (record.getExerciseName() == null || record.getExerciseName().isBlank()) {
            throw new IllegalArgumentException("운동 이름은 비어 있을 수 없습니다.");
        }

        if (record.getWeight() < 0) {
            throw new IllegalArgumentException("무게는 0 이상이어야 합니다.");
        }

        if (record.getReps() <= 0) {
            throw new IllegalArgumentException("반복 횟수는 1 이상이어야 합니다.");
        }

        if (record.getSets() <= 0) {
            throw new IllegalArgumentException("세트 수는 1 이상이어야 합니다.");
        }

        if (record.getWorkoutDate() == null) {
            throw new IllegalArgumentException("운동 날짜는 비어 있을 수 없습니다.");
        }
    }
}