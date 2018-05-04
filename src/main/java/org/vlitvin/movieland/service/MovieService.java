package org.vlitvin.movieland.service;


import org.vlitvin.movieland.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();
    Movie getById(int movieId);
    List<Movie> getThreeRandomMovies();
}
