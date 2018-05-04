package org.vlitvin.movieland.dao.impl.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.vlitvin.movieland.model.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getInt("id"));
        movie.setYearOfRelease(resultSet.getDate("year_of_release"));
        movie.setNameRussian(resultSet.getString("name_russian"));
        movie.setNameNative(resultSet.getString("name_native"));
        movie.setDescription(resultSet.getString("description"));
        movie.setPicturePath(resultSet.getString("picture_path"));
        movie.setRating(resultSet.getDouble("rating"));
        movie.setPrice(resultSet.getDouble("price"));
        return movie;
    }
}
