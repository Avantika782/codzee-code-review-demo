package com.example.dto;

import com.example.model.Task;
import java.util.List;

public record TaskPageResponse(
        List<Task> content,
        int page,
        int size,
        long totalElements,
        int totalPages
) {
}
