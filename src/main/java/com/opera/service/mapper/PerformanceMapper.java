package com.opera.service.mapper;

import com.opera.model.Performance;
import com.opera.model.dto.PerformanceRequestDto;
import com.opera.model.dto.PerformanceResponseDto;

public interface PerformanceMapper {
    PerformanceResponseDto toPerformanceDto(Performance performance);

    Performance toPerformanceEntity(PerformanceRequestDto performanceRequestDto);
}
