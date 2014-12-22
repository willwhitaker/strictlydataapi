package com.sportdataconnect.strictly.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Ordering;
import com.sportdataconnect.strictly.api.model.WeekId;

/**
 * Created by Will on 02/12/2014.
 */
public class FlattenedCoupleResult {

    private int year;
    private WeekId weekId;
    private CoupleResult coupleResult;

    public static final Ordering<FlattenedCoupleResult> CHRONOLOGICAL_ORDERING = new Ordering<FlattenedCoupleResult>() {
        @Override
        public int compare(FlattenedCoupleResult x, FlattenedCoupleResult y) {
            return WeekId.compare(x.weekId, y.weekId);
        }
    };

    public static final Ordering<FlattenedCoupleResult> SCORE_ORDERING = new Ordering<FlattenedCoupleResult>() {
        @Override
        public int compare(FlattenedCoupleResult x, FlattenedCoupleResult y) {
            return -Double.compare(x.getCoupleResult().getAverageScore(), y.getCoupleResult().getAverageScore());
        }
    };

    public FlattenedCoupleResult(int year, WeekId weekId, CoupleResult coupleResult) {
        this.year = year;
        this.weekId = weekId;
        this.coupleResult = coupleResult;
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
    public CoupleResult getCoupleResult() {
        return coupleResult;
    }
}
