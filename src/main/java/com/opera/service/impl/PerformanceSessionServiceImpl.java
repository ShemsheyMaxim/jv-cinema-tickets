package com.opera.service.impl;

import com.opera.dao.PerformanceSessionDao;
import com.opera.model.PerformanceSession;
import com.opera.service.PerformanceSessionService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformanceSessionServiceImpl implements PerformanceSessionService {
    private final PerformanceSessionDao performanceSessionDao;

    @Autowired
    public PerformanceSessionServiceImpl(PerformanceSessionDao performanceSessionDao) {
        this.performanceSessionDao = performanceSessionDao;
    }

    @Override
    public List<PerformanceSession> findAvailableSessions(Long performanceId, LocalDate date) {
        return performanceSessionDao.findAvailableSessions(performanceId, date);
    }

    @Override
    public PerformanceSession add(PerformanceSession performanceSession) {
        return performanceSessionDao.add(performanceSession);
    }

    @Override
    public PerformanceSession get(Long id) {
        return performanceSessionDao.get(id).orElseThrow(() ->
                new RuntimeException("Performance session for id " + id + " not found."));
    }

    @Override
    public PerformanceSession update(PerformanceSession performanceSession) {
        return performanceSessionDao.update(performanceSession);
    }

    @Override
    public PerformanceSession delete(Long performanceSessionId) {
        return performanceSessionDao.delete(performanceSessionId);
    }
}
