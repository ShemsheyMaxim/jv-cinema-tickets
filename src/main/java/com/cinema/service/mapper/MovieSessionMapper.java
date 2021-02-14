package com.cinema.service.mapper;

import com.cinema.model.MovieSession;
import com.cinema.model.dto.MovieSessionRequestDto;
import com.cinema.model.dto.MovieSessionResponseDto;

public interface MovieSessionMapper {
    MovieSessionResponseDto toMovieSessionDto(MovieSession movieSession);

    MovieSession toMovieSessionEntity(MovieSessionRequestDto movieSessionRequestDto);
}
