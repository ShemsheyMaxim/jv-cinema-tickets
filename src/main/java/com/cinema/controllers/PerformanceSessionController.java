package com.cinema.controllers;

import com.cinema.model.PerformanceSession;
import com.cinema.model.dto.PerformanceSessionRequestDto;
import com.cinema.model.dto.PerformanceSessionResponseDto;
import com.cinema.service.PerformanceSessionService;
import com.cinema.service.mapper.PerformanceSessionMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/performance-sessions")
public class PerformanceSessionController {
    private final PerformanceSessionMapper performanceSessionMapper;
    private final PerformanceSessionService performanceSessionService;

    @Autowired
    public PerformanceSessionController(PerformanceSessionMapper performanceSessionMapper,
                                        PerformanceSessionService performanceSessionService) {
        this.performanceSessionMapper = performanceSessionMapper;
        this.performanceSessionService = performanceSessionService;
    }

    @PostMapping
    public void create(@RequestBody @Valid PerformanceSessionRequestDto
                                   performanceSessionRequestDto) {
        PerformanceSession performanceSession =
                performanceSessionMapper.toPerformanceSessionEntity(performanceSessionRequestDto);
        performanceSessionService.add(performanceSession);
    }

    @GetMapping("/available")
    public List<PerformanceSessionResponseDto> getAvailablePerformanceSession(@RequestParam Long id,
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date) {
        return performanceSessionService.findAvailableSessions(id, date)
                .stream()
                .map(performanceSessionMapper::toPerformanceSessionDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody @Valid PerformanceSessionRequestDto
            performanceSessionRequestDto) {
        PerformanceSession updatePerformanceSession =
                performanceSessionMapper.toPerformanceSessionEntity(performanceSessionRequestDto);
        updatePerformanceSession.setId(id);
        performanceSessionService.update(updatePerformanceSession);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        performanceSessionService.delete(id);
    }
}
