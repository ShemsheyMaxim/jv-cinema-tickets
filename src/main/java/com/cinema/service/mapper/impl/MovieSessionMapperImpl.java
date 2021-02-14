package com.cinema.service.mapper.impl;

import com.cinema.model.CinemaHall;
import com.cinema.model.Movie;
import com.cinema.model.MovieSession;
import com.cinema.model.dto.MovieSessionRequestDto;
import com.cinema.model.dto.MovieSessionResponseDto;
import com.cinema.service.CinemaHallService;
import com.cinema.service.MovieService;
import com.cinema.service.mapper.MovieSessionMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapperImpl implements MovieSessionMapper {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    @Autowired
    public MovieSessionMapperImpl(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    @Override
    public MovieSessionResponseDto toMovieSessionDto(MovieSession movieSession) {
        MovieSessionResponseDto responseDto = new MovieSessionResponseDto();
        responseDto.setId(movieSession.getId());
        responseDto.setMovieTitle(movieSession.getMovie().getTitle());
        responseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        responseDto.setCinemaHallCapacity(movieSession.getCinemaHall().getCapacity());
        DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
        String formattedDateTime = movieSession.getShowTime().format(formatter);
        responseDto.setShowTime(formattedDateTime);
        return responseDto;
    }

    @Override
    public MovieSession toMovieSessionEntity(MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        Movie movie = movieService.get(movieSessionRequestDto.getMovieId());
        movieSession.setMovie(movie);
        CinemaHall cinemaHall = cinemaHallService.get(movieSessionRequestDto.getCinemaHallId());
        movieSession.setCinemaHall(cinemaHall);
        String dateString = movieSessionRequestDto.getShowTime();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        LocalDateTime showTime = LocalDateTime.parse(dateString, formatter);
        movieSession.setShowTime(showTime);
        return movieSession;
    }
}
