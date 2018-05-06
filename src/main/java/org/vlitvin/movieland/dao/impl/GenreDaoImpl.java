package org.vlitvin.movieland.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.vlitvin.movieland.dao.GenreDao;
import org.vlitvin.movieland.dao.impl.mappers.GenreMapper;
import org.vlitvin.movieland.dao.impl.mappers.MovieMapper;
import org.vlitvin.movieland.model.Genre;
import org.vlitvin.movieland.model.Movie;

import java.util.List;

@Service
public class GenreDaoImpl implements GenreDao {

    private static final String ALL_GENRES_SELECT = "select * from GENRE";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Genre> getAll() {
        return jdbcTemplate.query(ALL_GENRES_SELECT, new GenreMapper());
    }
}
