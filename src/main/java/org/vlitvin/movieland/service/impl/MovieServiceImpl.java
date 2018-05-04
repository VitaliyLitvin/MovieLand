package org.vlitvin.movieland.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vlitvin.movieland.dao.MovieDao;
import org.vlitvin.movieland.model.Movie;
import org.vlitvin.movieland.service.MovieService;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieDao movieDao;

    @Override
    public List<Movie> getAllMovies() {
        return movieDao.getAllMovies();
    }
}
