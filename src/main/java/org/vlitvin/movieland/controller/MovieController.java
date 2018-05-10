package org.vlitvin.movieland.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vlitvin.movieland.model.Movie;
import org.vlitvin.movieland.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/v1/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping()
    @ResponseBody
    public List<Movie> getAllMovies() {
        return movieService.getAll();
    }

    @GetMapping("/{movieId}")
    @ResponseBody
    public Movie getMovie(@PathVariable("movieId") int movieId, @RequestParam(required = false) String currency) {
        Movie movie;
        if (currency != null) {
            movie = movieService.getMovie(movieId, currency);
        } else {
            movie = movieService.getMovie(movieId);
        }
        return movie;
    }

    @GetMapping("/random")
    @ResponseBody
    public List<Movie> getThreeRandomMovies() {
        return movieService.getThreeRandomMovies();
    }

    @GetMapping("genre/{genreId}")
    @ResponseBody
    public List<Movie> getMovieByGenreId(@PathVariable("genreId") int genreId) {
        return movieService.getByGenreId(genreId);
    }
}
