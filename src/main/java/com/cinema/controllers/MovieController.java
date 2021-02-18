package com.cinema.controllers;

import com.cinema.model.Movie;
import com.cinema.model.dto.MovieRequestDto;
import com.cinema.model.dto.MovieResponseDto;
import com.cinema.service.MovieService;
import com.cinema.service.mapper.MovieMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieMapper movieMapper;
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieMapper movieMapper, MovieService movieService) {
        this.movieMapper = movieMapper;
        this.movieService = movieService;
    }

    @PostMapping
    public void create(@RequestBody @Valid MovieRequestDto movieRequestDto) {
        Movie movie = movieMapper.toMovieEntity(movieRequestDto);
        movieService.add(movie);
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll()
                .stream()
                .map(movieMapper::toMovieDto)
                .collect(Collectors.toList());
    }
}
