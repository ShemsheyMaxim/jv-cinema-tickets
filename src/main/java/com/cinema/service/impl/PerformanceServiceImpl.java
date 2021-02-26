package com.cinema.service.impl;

import com.cinema.dao.PerformanceDao;
import com.cinema.model.Performance;
import com.cinema.service.PerformanceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformanceServiceImpl implements PerformanceService {
    private final PerformanceDao performanceDao;

    @Autowired
    public PerformanceServiceImpl(PerformanceDao performanceDao) {
        this.performanceDao = performanceDao;
    }

    @Override
    public Performance add(Performance performance) {
        return performanceDao.add(performance);
    }

    @Override
    public List<Performance> getAll() {
        return performanceDao.getAll();
    }

    @Override
    public Performance get(Long performanceId) {
        return performanceDao.get(performanceId).orElseThrow(() ->
                new RuntimeException("Performance for id " + performanceId + " not found."));
    }
}
