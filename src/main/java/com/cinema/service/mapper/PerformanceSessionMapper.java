package com.cinema.service.mapper;

import com.cinema.model.PerformanceSession;
import com.cinema.model.dto.PerformanceSessionRequestDto;
import com.cinema.model.dto.PerformanceSessionResponseDto;

public interface PerformanceSessionMapper {
    PerformanceSessionResponseDto toPerformanceSessionDto(PerformanceSession performanceSession);

    PerformanceSession toPerformanceSessionEntity(PerformanceSessionRequestDto
                                                          performanceSessionRequestDto);
}
