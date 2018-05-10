package org.vlitvin.movieland.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vlitvin.movieland.dao.MovieDao;
import org.vlitvin.movieland.dao.RateDao;
import org.vlitvin.movieland.model.Movie;
import org.vlitvin.movieland.service.MovieService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieDao movieDao;

    @Autowired
    RateDao rateDao;

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public Movie getMovie(int movieId) {
        return movieDao.getMovie(movieId);
    }

    @Override
    public Movie getMovie(int movieId, String currency) {
        Movie movie = getMovie(movieId);
        double rate = rateDao.getRateByCurrency(currency);
        double priceInUAH = movie.getPrice();
        double price = priceInUAH / rate;
        movie.setPrice(price);
        return movie;
    }

    @Override
    public List<Movie> getThreeRandomMovies() {
        List<Movie> movies = new ArrayList<>(3);
        while (movies.size() < 3) {
            Movie movie = getRandomMovie();
            if (movie != null) {
                movies.add(movie);
            }
        }
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
        return movieDao.getMovie(randomMovieId);
    }

}
