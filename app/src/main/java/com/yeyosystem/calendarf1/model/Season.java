package com.yeyosystem.calendarf1.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeyo7 on 10/5/2017.
 */
@IgnoreExtraProperties
public class Season {

    private String seasonId;
    private String year;

    private DatabaseReference mDatabase;

    private Season() {
        // Default constructor required for calls to DataSnapshot.getValue(Season.class)
    }

    private Season(String seasonId, String year, boolean active) {
        this.seasonId = seasonId;
        this.year = year;
    }

    @Exclude
    private Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("seasonId", seasonId);
        result.put("year", year);

        return result;
    }

    private void writeNewSeason(String year, boolean active) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String key = mDatabase.child("seasons").push().getKey();
        Season season = new Season(key, year, active);
        Map<String, Object> seasonValues = season.toMap();
        seasonValues.put("/seasons/" + key, seasonValues);
        mDatabase.updateChildren(seasonValues);
    }

}
