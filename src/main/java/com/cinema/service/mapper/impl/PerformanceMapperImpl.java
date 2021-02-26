package com.cinema.service.mapper.impl;

import com.cinema.model.Performance;
import com.cinema.model.dto.PerformanceRequestDto;
import com.cinema.model.dto.PerformanceResponseDto;
import com.cinema.service.mapper.PerformanceMapper;
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
