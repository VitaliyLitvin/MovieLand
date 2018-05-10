package org.vlitvin.movieland.dao.impl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.vlitvin.movieland.dao.RateDao;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class NBURateDaoImpl implements RateDao {

    private final ConcurrentHashMap<String, Double> rates = new ConcurrentHashMap<>();
    private final static List<String> currenciesList = Arrays.asList("USD", "EUR");
    private final static String GET_RATE_BY_CURRENCY_URL_TEMPLATE = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=:currency&date=:date&json";

    @PostConstruct
    private void init() {
        for (String currency : currenciesList) {
            Double rate = getCurrencyByRate(currency);
            rates.put(currency, rate);
        }
    }

    private Double getCurrencyByRate(String currency) {
        double rate = 0;
        RestTemplate restTemplate = new RestTemplate();
        String url = GET_RATE_BY_CURRENCY_URL_TEMPLATE.replaceFirst(":currency", currency);
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentDate = dateFormat.format(new Date());
        url = url.replaceFirst(":date", currentDate);
        String responseFromNBUJson = restTemplate.getForObject(url, String.class);
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(responseFromNBUJson);
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            rate = (double) jsonObject.get("rate");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rate;
    }

    @Override
    public double getRateByCurrency(String currency) {
        return rates.get(currency);
    }

    @Scheduled(cron = "0 1 01 * * *")
    public void updateCache() {
        init();
    }
}
