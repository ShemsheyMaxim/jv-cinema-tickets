package com.opera.dao;

import com.opera.model.Performance;
import java.util.List;
import java.util.Optional;

public interface PerformanceDao {
    Performance add(Performance performance);

    List<Performance> getAll();

    Optional<Performance> get(Long performanceId);
}
