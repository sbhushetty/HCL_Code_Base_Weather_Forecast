package com.weather;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @RequestMapping(value="/getforecast/latitude/{latitude}/longitude/{longitude}", produces = { "application/json" }, method = RequestMethod.GET)
    public JSONObject getForecast(@PathVariable("latitude") String latitude, @PathVariable("longitude") String longitude) {
        return weatherService.createService(latitude,longitude);
    }

}
