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

    @Override
    public String toString() {
        return "FlattenedCoupleResult{" +
                "year=" + year +
                ", weekId=" + weekId +
                ", coupleResult=" + coupleResult +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlattenedCoupleResult that = (FlattenedCoupleResult) o;

        if (year != that.year) return false;
        if (!weekId.equals(that.weekId)) return false;
        return coupleResult.equals(that.coupleResult);
    }

    @Override
    public int hashCode() {
        int result = year;
        result = 31 * result + weekId.hashCode();
        result = 31 * result + coupleResult.hashCode();
        return result;
    }
}
