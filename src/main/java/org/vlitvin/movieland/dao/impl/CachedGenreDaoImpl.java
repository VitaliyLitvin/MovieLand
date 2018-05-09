package org.vlitvin.movieland.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.vlitvin.movieland.dao.GenreDao;
import org.vlitvin.movieland.dao.impl.mappers.GenreMapper;
import org.vlitvin.movieland.model.Genre;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class CachedGenreDaoImpl implements GenreDao {
    private volatile List<Genre> cachedGenre;

    @Autowired
    @Qualifier("genreDaoImpl")
    GenreDao genreDao;

    @PostConstruct
    private void init() {
        cachedGenre = genreDao.getAll();
    }

    @Override
    public List<Genre> getAll() {
        return new ArrayList<>(cachedGenre);
    }

    @Scheduled(cron = "0 0 */4 * * *")
    public void updateCache() {
        init();
    }
}
