package com.yeyosystem.calendarf1.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeyo7 on 8/5/2017.
 */
@IgnoreExtraProperties
public class Calendar {

    private String calendarId;
    private String seasonId;
    private String trackId;
    private String practice1;
    private String practice2;
    private String practice3;
    private String qualifying;
    private String race;
    private String calendarUTC;

    private Calendar() {
        // Default constructor required for calls to DataSnapshot.getValue(Calendar.class)
    }

    private Calendar(String calendarId, String seasonId, String trackId, String practice1, String practice2, String practice3, String qualifying, String race, String calendarUTC) {
        this.calendarId = calendarId;
        this.seasonId = seasonId;
        this.trackId = trackId;
        this.practice1 = practice1;
        this.practice2 = practice2;
        this.practice3 = practice3;
        this.qualifying = qualifying;
        this.race = race;
        this.calendarUTC = calendarUTC;
    }

    @Exclude
    private Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("calendarId", calendarId);
        result.put("seasonId", seasonId);
        result.put("trackId", trackId);
        result.put("practice1", practice1);
        result.put("practice2", practice2);
        result.put("practice3", practice3);
        result.put("qualifying", qualifying);
        result.put("race",race);
        result.put("calendarUTC", calendarUTC);
        return result;
    }

    private DatabaseReference mDatabase;

    private void writeNewRace(String year, boolean active) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String key = mDatabase.child("posts").push().getKey();
        Calendar calendar = new Calendar(/*key, year, active*/);
        Map<String, Object> raceValues = calendar.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/races/" + key, raceValues);
        //childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }
}
