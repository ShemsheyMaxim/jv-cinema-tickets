package com.cinema.service.mapper;

import com.cinema.model.CinemaHall;
import com.cinema.model.dto.CinemaHallRequestDto;
import com.cinema.model.dto.CinemaHallResponseDto;

public interface CinemaHallMapper {
    CinemaHallResponseDto toCinemaHallDto(CinemaHall cinemaHall);

    CinemaHall toCinemaHallEntity(CinemaHallRequestDto requestDto);
}
