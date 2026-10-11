package com.example.service;

import com.example.model.Task;
import com.example..dto.TaskPageResponse;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public Task create(Task task) {
        long id = idGenerator.incrementAndGet();

        Task saved = new Task(id, task.getTitle(), task.getStatus());
        tasks.put(id, saved);

        return saved;
    }

    public List<Task> getAll() {
        return new ArrayList<>(tasks.values());
    }

    public Task getById(Long id) {
        return tasks.get(id);
    }

    public Task update(Long id, Task task) {
        if (!tasks.containsKey(id)) {
            return null;
        }

        Task updated = new Task(id, task.getTitle(), task.getStatus());
        tasks.put(id, updated);

        return updated;
    }

    public boolean delete(Long id) {
        return tasks.remove(id) != null;
    }

    public TaskPageResponse getPage(int page, int size) {
        if (page < 0 || size < 1 || size > 100) {
            throw new IllegalArgumentException(
                "Page must be non-negative and size must be between 1 and 100"
        );
        }

        List<Task> allTasks = tasks.values().stream()
            .sorted(Comparator.comparing(Task::getId))
            .toList();

        long totalElements = allTasks.size();
        int totalPages = (int) Math.ceil(
            (double) totalElements / size
        );

        long startLong = (long) page * size;

        if (startLong >= totalElements) {
        return new TaskPageResponse(
                List.of(), page, size, totalElements, totalPages
        );
        }

        int start = (int) startLong;
        int end = (int) Math.min(startLong + size, totalElements);

        return new TaskPageResponse(
            allTasks.subList(start, end),
            page,
            size,
            totalElements,
            totalPages
        );
    }
}
