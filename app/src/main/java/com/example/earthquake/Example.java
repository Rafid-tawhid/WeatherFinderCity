package com.example.earthquake;

import com.example.earthquake.pojo.CurrentEarthquakeResponse;
import com.example.earthquake.weatherpojo.Main;
import com.google.gson.annotations.SerializedName;

public class Example {
    @SerializedName("main")
    Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
