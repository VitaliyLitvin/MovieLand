package org.vlitvin.movieland.dao.impl.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.vlitvin.movieland.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper  implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
        Genre genre= new Genre();
        genre.setId(resultSet.getInt("id"));
        genre.setName(resultSet.getString("name"));
        return genre;
    }
}
