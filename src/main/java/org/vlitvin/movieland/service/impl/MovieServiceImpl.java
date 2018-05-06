package org.vlitvin.movieland.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vlitvin.movieland.dao.MovieDao;
import org.vlitvin.movieland.model.Movie;
import org.vlitvin.movieland.service.MovieService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieDao movieDao;

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public Movie getById(int movieId) {
        return movieDao.getById(movieId);
    }

    @Override
    public List<Movie> getThreeRandomMovies() {
        List<Movie> movies = new ArrayList<>(3);
        do {
            Movie movie = getRandomMovie();
            if (movie != null) {
                movies.add(movie);
            }
        } while (movies.size() == 3);
        return movies;
    }

    @Override
    public List<Movie> getByGenreId(int genreId) {
        return movieDao.getByGenreId(genreId);
    }

    private Movie getRandomMovie() {
        int maxMovieId = movieDao.getMaxMovieId();
        Random random = new Random();
        int randomMovieId = random.nextInt(maxMovieId);
        return movieDao.getById(randomMovieId);
    }
}
