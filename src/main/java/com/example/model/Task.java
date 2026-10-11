package com.example.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class Task {
    private Long id;

    @NotBlank(message = "Task title cannot be blank")
    private String title;

    @Pattern(
        regexp = "OPEN|IN_PROGRESS|DONE",
        message = "Status must be OPEN, IN_PROGRESS, or DONE"
    )
    private String status;

    public Task() {
    }

    public Task(Long id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
