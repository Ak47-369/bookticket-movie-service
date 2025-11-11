package com.bookticket.movie_service.controller;

import com.bookticket.movie_service.dto.CreateMovieRequest;
import com.bookticket.movie_service.dto.MovieResponse;
import com.bookticket.movie_service.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    @PreAuthorize("hasRole('THEATER_OWNER')")
    public ResponseEntity<MovieResponse> createMovie(@RequestBody CreateMovieRequest createMovieRequest) {
        return ResponseEntity.ok(movieService.createMovie(createMovieRequest));
    }
}
