package com.opera.service;

import com.opera.model.Performance;
import java.util.List;

public interface PerformanceService {
    Performance add(Performance performance);

    List<Performance> getAll();

    Performance get(Long performanceId);
}
