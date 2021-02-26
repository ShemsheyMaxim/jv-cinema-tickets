package com.cinema.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PerformanceRequestDto {
    @NotNull
    @NotEmpty
    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
