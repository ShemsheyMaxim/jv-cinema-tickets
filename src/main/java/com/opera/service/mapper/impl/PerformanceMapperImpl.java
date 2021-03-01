package com.opera.service.mapper.impl;

import com.opera.model.Performance;
import com.opera.model.dto.PerformanceRequestDto;
import com.opera.model.dto.PerformanceResponseDto;
import com.opera.service.mapper.PerformanceMapper;
import org.springframework.stereotype.Component;

@Component
public class PerformanceMapperImpl implements PerformanceMapper {
    @Override
    public PerformanceResponseDto toPerformanceDto(Performance performance) {
        PerformanceResponseDto responseDto = new PerformanceResponseDto();
        responseDto.setId(performance.getId());
        responseDto.setDescription(performance.getDescription());
        responseDto.setTitle(performance.getTitle());
        return responseDto;
    }

    @Override
    public Performance toPerformanceEntity(PerformanceRequestDto performanceRequestDto) {
        Performance performance = new Performance();
        performance.setTitle(performanceRequestDto.getTitle());
        performance.setDescription(performanceRequestDto.getDescription());
        return performance;
    }
}
