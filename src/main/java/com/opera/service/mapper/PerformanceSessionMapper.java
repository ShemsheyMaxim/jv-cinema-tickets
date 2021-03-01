package com.opera.service.mapper;

import com.opera.model.PerformanceSession;
import com.opera.model.dto.PerformanceSessionRequestDto;
import com.opera.model.dto.PerformanceSessionResponseDto;

public interface PerformanceSessionMapper {
    PerformanceSessionResponseDto toPerformanceSessionDto(PerformanceSession performanceSession);

    PerformanceSession toPerformanceSessionEntity(PerformanceSessionRequestDto
                                                          performanceSessionRequestDto);
}
