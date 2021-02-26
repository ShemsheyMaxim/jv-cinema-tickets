package com.cinema.service.mapper;

import com.cinema.model.Performance;
import com.cinema.model.dto.PerformanceRequestDto;
import com.cinema.model.dto.PerformanceResponseDto;

public interface PerformanceMapper {
    PerformanceResponseDto toPerformanceDto(Performance performance);

    Performance toPerformanceEntity(PerformanceRequestDto performanceRequestDto);
}
