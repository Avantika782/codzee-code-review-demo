package com.example.dto;

import com.example.taskapi.model.Task;
import java.util.List;

public record TaskPageResponse(
        List<Task> content,
        int page,
        int size,
        long totalElements,
        int totalPages
) {
}
