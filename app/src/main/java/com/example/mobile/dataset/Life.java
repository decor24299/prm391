package com.example.mobile.dataset;

import com.example.mobile.common.validation.DateValidator;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Life extends Entity {

    private String name;

    private String description;

    private String filmingLocation;

    private String timeStart;

    private String timeEnd;

    private int numberOfFilming;

    public Life() {

    }

    public Life(String name, String description, String filmingLocation, String timeStart, String timeEnd, int numberOfFilming) {
        this.name = name;
        this.description = description;
        this.filmingLocation = filmingLocation;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.numberOfFilming = numberOfFilming;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilmingLocation() {
        return filmingLocation;
    }

    public void setFilmingLocation(String filmingLocation) {
        this.filmingLocation = filmingLocation;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public int getNumberOfFilming() {
        return numberOfFilming;
    }

    public void setNumberOfFilming(int numberOfFilming) {
        this.numberOfFilming = numberOfFilming;
    }

    public String validate() {
        if (!DateValidator.validate(timeStart)) {
            return "Invalid date";
        }

        if (!DateValidator.validate(timeEnd)) {
            return "Invalid date";
        }

        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String serialize() throws JSONException {
        JSONObject jo = new JSONObject();
        jo.put("name", name);
        jo.put("description", description);
        jo.put("filmingLocation", filmingLocation);
        jo.put("timeStart", timeStart);
        jo.put("timeEnd", timeEnd);
        jo.put("numberOfFilming", numberOfFilming);

        return jo.toString();
    }
}
