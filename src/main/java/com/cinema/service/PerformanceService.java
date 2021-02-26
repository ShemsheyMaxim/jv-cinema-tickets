package com.cinema.service;

import com.cinema.model.Performance;
import java.util.List;

public interface PerformanceService {
    Performance add(Performance performance);

    List<Performance> getAll();

    Performance get(Long performanceId);
}
