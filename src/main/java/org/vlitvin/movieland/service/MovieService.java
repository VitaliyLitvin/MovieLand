package org.vlitvin.movieland.service;


import org.vlitvin.movieland.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();

    Movie getMovie(int movieId);

    Movie getMovie(int movieId, String currency);

    List<Movie> getThreeRandomMovies();

    List<Movie> getByGenreId(int genreId);
}
