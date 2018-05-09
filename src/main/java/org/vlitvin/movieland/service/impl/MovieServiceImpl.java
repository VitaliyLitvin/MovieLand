package org.vlitvin.movieland.service.impl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.vlitvin.movieland.dao.MovieDao;
import org.vlitvin.movieland.model.Movie;
import org.vlitvin.movieland.service.MovieService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {

    private final static String GET_RATE_BY_CURRENCY_URL_TEMPL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=:currency&date=:date&json";

    @Autowired
    MovieDao movieDao;

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public Movie getById(int movieId) {
        return movieDao.getById(movieId);
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

    @Override
    public void convertPriceByCurrency(Movie movie, String currency) {
        double rate = getRateByCurrency(currency);
        double priceInUAH = movie.getPrice();
        double newPrice = priceInUAH * rate;
        movie.setPrice(newPrice);
    }

    private double getRateByCurrency(String currency) {
        RestTemplate restTemplate = new RestTemplate();
        String url = GET_RATE_BY_CURRENCY_URL_TEMPL.replaceFirst(":currency", currency);
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentDate = dateFormat.format(new Date()); //2016/11/16 12:08:43
        url = url.replaceFirst(":date", currentDate);
        String responsFromNBU = restTemplate.getForObject(url, String.class);

        JSONParser parser1 = new JSONParser();
        Object obj = null;
        try {
            obj = parser1.parse(responsFromNBU);
            JSONArray array = (JSONArray)obj;
            for (Object o : array) {
                JSONObject current = (JSONObject) o;
                double v = (double) current.get("rate");
                System.out.println(v);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }



        responsFromNBU = responsFromNBU.substring(0, responsFromNBU.length() - 1);
        responsFromNBU = responsFromNBU.substring(1, responsFromNBU.length());




        JSONParser parser = new JSONParser();
        Double aDouble  = null;
        try {
            JSONObject json = (JSONObject) parser.parse(responsFromNBU);
            aDouble  = (Double) json.get("rate");
            json.size();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return aDouble;
    }

    private Movie getRandomMovie() {
        int maxMovieId = movieDao.getMaxMovieId();
        Random random = new Random();
        int randomMovieId = random.nextInt(maxMovieId);
        return movieDao.getById(randomMovieId);
    }

    private class Currency {
        double rate;
        String cc;
    }

}
