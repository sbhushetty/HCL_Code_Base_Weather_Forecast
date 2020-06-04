package com.weather;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class WeatherService {

    @Autowired
    RestTemplate restTemplate;

    public JSONObject createService(String latitude, String longitude) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);
        String request = latitude+","+longitude;
        String latitudeLongitudeResponse = restTemplate.exchange("https://api.weather.gov/points/"+request, HttpMethod.GET, entity, String.class).getBody();
        System.out.println(latitudeLongitudeResponse);
        Object obj = JSONValue.parse(latitudeLongitudeResponse);
        JSONObject responseJsonObject = (JSONObject)obj;
        JSONObject propertiesResponse = (JSONObject)responseJsonObject.get("properties");
        System.out.println(propertiesResponse);
        String forecastURL = (String) propertiesResponse.get("forecast");
        System.out.println(forecastURL);
        String forecastResposne = restTemplate.exchange(forecastURL, HttpMethod.GET, entity, String.class).getBody();
        Object forecastResposneObject = JSONValue.parse(forecastResposne);
        JSONObject responseForecastJsonObject = (JSONObject)forecastResposneObject;
        return responseForecastJsonObject;
    }
}
