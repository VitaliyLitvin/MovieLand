package org.vlitvin.movieland.dao;

import org.vlitvin.movieland.model.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> getAllMovies();
}
