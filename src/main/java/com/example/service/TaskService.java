package com.example.service;

import com.example.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}