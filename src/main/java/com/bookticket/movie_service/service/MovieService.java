package com.bookticket.movie_service.service;

import com.bookticket.movie_service.dto.CreateMovieRequest;
import com.bookticket.movie_service.dto.MovieResponse;
import com.bookticket.movie_service.entity.Movie;
import com.bookticket.movie_service.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieResponse createMovie(CreateMovieRequest request) {
        log.info("Creating movie with name: {}", request.name());
        Movie movie = new Movie();
        movie.setName(request.name());
        movie.setDescription(request.description());
        movie.setImage(request.image());
        movie.setTrailer(request.trailer());
        movie.setCast(request.cast());
        movie.setGenre(request.genre().stream().toList());
        movie.setLanguage(request.language());
        movie.setCertificate(request.certificate().stream().toList());
        movie.setDuration(request.duration());
        try {
            Movie savedMovie = movieRepository.save(movie);
            log.info("Movie created with id: {}", savedMovie.getId());
            return new MovieResponse(movie.getId(), movie.getName(), movie.getDescription());
        } catch (Exception e) {
            log.error("Error creating movie: {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
