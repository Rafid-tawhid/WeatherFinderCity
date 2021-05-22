package com.example.earthquake;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface EarthquakeInterface {
    String stdate="2014-01-01";
    String enddate="2014-01-02";
    String c="geojson";

    @GET("query")
    Call<Example2> getEarthquake(@Query("format") String geojson,@Query("starttime") String stdate,@Query("endtime") String enddate);




}
