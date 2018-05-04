package org.vlitvin.movieland.dao.impl.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.vlitvin.movieland.model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryMapper implements RowMapper<Country> {
    @Override
    public Country mapRow(ResultSet resultSet, int i) throws SQLException {
        Country country = new Country();
        country.setId(resultSet.getInt("id"));
        country.setName(resultSet.getString("name"));
        return country;
    }
}
