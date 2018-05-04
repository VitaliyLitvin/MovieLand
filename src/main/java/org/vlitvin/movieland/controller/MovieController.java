package org.vlitvin.movieland.controller;

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
    public Movie getMovieById(@PathVariable("movieId") int movieId) {
        return movieService.getById(movieId);
    }

    @GetMapping("/random")
    @ResponseBody
    public List<Movie> getThreeRandomMovies(){
        return movieService.getThreeRandomMovies();
    }
}
