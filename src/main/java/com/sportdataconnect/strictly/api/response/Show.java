package com.sportdataconnect.strictly.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportdataconnect.strictly.api.model.WeekId;

import java.util.List;

/**
 * Created by Will on 28/11/2014.
 */
public class Show {

    private int year;
    private WeekId weekId;

    private List<CoupleResult> couples;

    public Show(int year, WeekId weekId, List<CoupleResult> couples) {
        this.year = year;
        this.weekId = weekId;
        this.couples = couples;
    }

    @JsonProperty
    public int getYear() {
        return year;
    }

    @JsonProperty
    public WeekId getWeekId() {
        return weekId;
    }

    @JsonProperty
    public List<CoupleResult> getCouples() {
        return couples;
    }
}
