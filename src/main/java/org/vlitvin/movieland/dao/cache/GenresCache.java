package org.vlitvin.movieland.dao.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.vlitvin.movieland.dao.impl.mappers.GenreMapper;
import org.vlitvin.movieland.model.Genre;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class GenresCache {

    private static final String ALL_GENRES_SELECT = "select * from GENRE";

    @Autowired
    JdbcTemplate jdbcTemplate;

    private List<Genre> cachedGenre;

    public List<Genre> getAllGenre() {
        return new ArrayList<>(cachedGenre);
    }

    @PostConstruct
    private void init() {
        cachedGenre = jdbcTemplate.query(ALL_GENRES_SELECT, new GenreMapper());
    }
    @Scheduled(cron = "* * */4 * * *")
    private void updateCache(){
        cachedGenre = jdbcTemplate.query(ALL_GENRES_SELECT, new GenreMapper());
    }
}
