package com.cinema.model.dto;

public class PerformanceSessionResponseDto {
    private Long id;
    private String performanceTitle;
    private Long stageId;
    private int stageCapacity;
    private String showTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerformanceTitle() {
        return performanceTitle;
    }

    public void setPerformanceTitle(String performanceTitle) {
        this.performanceTitle = performanceTitle;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public int getStageCapacity() {
        return stageCapacity;
    }

    public void setStageCapacity(int stageCapacity) {
        this.stageCapacity = stageCapacity;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}
