package com.opera.model.dto;

import javax.validation.constraints.NotNull;

public class PerformanceSessionRequestDto {
    @NotNull
    private Long performanceId;
    @NotNull
    private Long stageId;
    @NotNull
    private String showTime;

    public Long getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Long performanceId) {
        this.performanceId = performanceId;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}
