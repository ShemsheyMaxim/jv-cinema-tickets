package com.cinema.service.mapper;

import com.cinema.model.Movie;
import com.cinema.model.dto.MovieRequestDto;
import com.cinema.model.dto.MovieResponseDto;

public interface MovieMapper {
    MovieResponseDto toMovieDto(Movie movie);

    Movie toMovieEntity(MovieRequestDto movieRequestDto);
}
