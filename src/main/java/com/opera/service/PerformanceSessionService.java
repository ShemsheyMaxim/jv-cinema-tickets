package com.opera.service;

import com.opera.model.PerformanceSession;
import java.time.LocalDate;
import java.util.List;

public interface PerformanceSessionService {
    List<PerformanceSession> findAvailableSessions(Long performanceId, LocalDate date);

    PerformanceSession add(PerformanceSession performanceSession);

    PerformanceSession get(Long id);

    PerformanceSession update(PerformanceSession performanceSession);

    PerformanceSession delete(Long performanceSessionId);
}
