package com.coding4lif3.sunshine.network.service;

import com.coding4lif3.sunshine.data.Weather;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by federicomonaco on 9/28/14.
 */
public interface WeatherService {

    // user subscription
    @GET("/data/2.5/forecast/daily")
    Weather weatherRequest(@Query("q") long cityCode,
                                 @Query("mode") String outputMode,
                                 @Query("units") String units,
                                 @Query("cnt") long numberOfDays);
}
