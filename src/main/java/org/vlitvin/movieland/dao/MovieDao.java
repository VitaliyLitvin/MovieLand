package org.vlitvin.movieland.dao;

import org.vlitvin.movieland.model.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> getAll();

    Movie getMovie(int movieId);

    int getMaxMovieId();

    List<Movie> getByGenreId(int genreId);
}
