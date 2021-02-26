package com.cinema.dao;

import com.cinema.model.PerformanceSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PerformanceSessionDao {
    PerformanceSession add(PerformanceSession performanceSession);

    List<PerformanceSession> findAvailableSessions(Long performanceId, LocalDate date);

    PerformanceSession update(PerformanceSession performanceSession);

    PerformanceSession delete(Long performanceSessionId);

    Optional<PerformanceSession> get(Long id);
}
