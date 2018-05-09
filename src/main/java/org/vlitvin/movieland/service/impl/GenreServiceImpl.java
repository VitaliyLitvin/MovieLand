package org.vlitvin.movieland.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.vlitvin.movieland.dao.GenreDao;
import org.vlitvin.movieland.model.Genre;
import org.vlitvin.movieland.service.GenreService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    @Qualifier("genreDaoImpl")
    GenreDao genreDao;

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }
}
