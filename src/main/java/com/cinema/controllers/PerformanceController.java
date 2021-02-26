package com.cinema.controllers;

import com.cinema.model.Performance;
import com.cinema.model.dto.PerformanceRequestDto;
import com.cinema.model.dto.PerformanceResponseDto;
import com.cinema.service.PerformanceService;
import com.cinema.service.mapper.PerformanceMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/performances")
public class PerformanceController {
    private final PerformanceMapper performanceMapper;
    private final PerformanceService performanceService;

    @Autowired
    public PerformanceController(PerformanceMapper performanceMapper,
                                 PerformanceService performanceService) {
        this.performanceMapper = performanceMapper;
        this.performanceService = performanceService;
    }

    @PostMapping
    public void create(@RequestBody @Valid PerformanceRequestDto performanceRequestDto) {
        Performance performance = performanceMapper.toPerformanceEntity(performanceRequestDto);
        performanceService.add(performance);
    }

    @GetMapping
    public List<PerformanceResponseDto> getAll() {
        return performanceService.getAll()
                .stream()
                .map(performanceMapper::toPerformanceDto)
                .collect(Collectors.toList());
    }
}
