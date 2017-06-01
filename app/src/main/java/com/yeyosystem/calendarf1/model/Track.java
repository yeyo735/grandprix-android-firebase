package com.yeyosystem.calendarf1.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeyo7 on 12/5/2017.
 */

public class Track {

    private String TrackId;
    private String Country;
    private String Alpha3;
    private String City;
    private String Circuit;
    private String GMap;

    private Track(String trackId, String country, String alpha3, String city, String circuit, String GMap) {
        TrackId = trackId;
        Country = country;
        Alpha3 = alpha3;
        City = city;
        Circuit = circuit;
        this.GMap = GMap;
    }

    // [START post_to_map]
    @Exclude
    private Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("trackId", TrackId);
        result.put("country", Country);
        result.put("alpha3", Alpha3);
        result.put("city", City);
        result.put("circuit", Circuit);
        result.put("gMap", GMap);

        return result;
    }
    // [END post_to_map]
}
