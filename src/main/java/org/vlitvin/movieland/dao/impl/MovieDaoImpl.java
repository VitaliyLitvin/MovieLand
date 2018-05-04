package org.vlitvin.movieland.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.vlitvin.movieland.dao.MovieDao;
import org.vlitvin.movieland.dao.impl.mappers.CountryMapper;
import org.vlitvin.movieland.dao.impl.mappers.GenreMapper;
import org.vlitvin.movieland.dao.impl.mappers.MovieMapper;
import org.vlitvin.movieland.model.Country;
import org.vlitvin.movieland.model.Genre;
import org.vlitvin.movieland.model.Movie;


import java.util.List;

@Repository
public class MovieDaoImpl implements MovieDao {

    private static final String ALL_MOVIES_SELECT = "select * from MOVIE";
    private static final String MOVIE_BY_ID_SELECT = "select * from MOVIE where ID = ?";
    private static final String MAX_MOVIE_ID_SELECT = "select max(ID) from MOVIE";
    private static final String COUNTRIES_BY_MOVIE_ID_SELECT = "select * from MOVIE_COUNTRY mc join COUNTRY c on mc.COUNTRY_ID = c.ID where mc.MOVIE_ID = ?";
    private static final String GENRES_BY_MOVIE_ID_SELECT = "select * from MOVIE_GENRE mg join GENRE g on mg.GENRE_ID = g.ID where MOVIE_ID = ?";


//    @Autowired
//    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Movie> getAll() {
//        HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
        List<Movie> movies = jdbcTemplate.query(ALL_MOVIES_SELECT, new MovieMapper());
        for (Movie movie : movies) {
            movie.setCountries(getCountriesByMovieId(movie.getId()));
            movie.setGenres(getGenresByMovieId(movie.getId()));
        }
        return movies;
    }

    @Override
    public Movie getById(int movieId) {
        Movie movie = jdbcTemplate.queryForObject(MOVIE_BY_ID_SELECT,new Object[]{movieId}, new MovieMapper());
        movie.setGenres(getGenresByMovieId(movieId));
        movie.setCountries(getCountriesByMovieId(movieId));
        return movie;
    }

    @Override
    public int getMaxMovieId() {

        return 0;
    }

    private List<Genre> getGenresByMovieId(int movie_id) {
        return jdbcTemplate.query(GENRES_BY_MOVIE_ID_SELECT, new Object[]{movie_id}, new GenreMapper());
    }

    private List<Country> getCountriesByMovieId(int movie_id) {
        return jdbcTemplate.query(COUNTRIES_BY_MOVIE_ID_SELECT, new Object[]{movie_id}, new CountryMapper());
    }


}
