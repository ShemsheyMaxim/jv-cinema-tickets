package com.cinema.service.impl;

import com.cinema.dao.CinemaHallDao;
import com.cinema.model.CinemaHall;
import com.cinema.service.CinemaHallService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    private final CinemaHallDao cinemaHallDao;

    @Autowired
    public CinemaHallServiceImpl(CinemaHallDao cinemaHallDao) {
        this.cinemaHallDao = cinemaHallDao;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }

    @Override
    public CinemaHall get(Long cinemaHallId) {
        return cinemaHallDao.get(cinemaHallId).orElseThrow(() ->
                new RuntimeException("Cinema hall for id: " + cinemaHallId + " not found."));
    }
}
