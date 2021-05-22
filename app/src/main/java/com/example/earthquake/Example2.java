package com.example.earthquake;

import com.example.earthquake.pojo.Feature;
import com.example.earthquake.weatherpojo.Main;
import com.google.gson.annotations.SerializedName;

public class Example2 {
    @SerializedName("type")
    Feature feature;

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}
