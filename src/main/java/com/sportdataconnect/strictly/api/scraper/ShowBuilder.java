package com.sportdataconnect.strictly.api.scraper;

import com.google.common.collect.ImmutableList;
import com.sportdataconnect.strictly.api.model.WeekId;
import com.sportdataconnect.strictly.api.response.CoupleResult;
import com.sportdataconnect.strictly.api.response.Show;

/**
 * Created by Will on 28/11/2014.
 */
public class ShowBuilder {

    private WeekId weekId;
    private int year;
    private ImmutableList.Builder<CoupleResult> results = ImmutableList.builder();

    public ShowBuilder(WeekId weekId) {
        this.weekId = weekId;
    }

    public ShowBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    public ShowBuilder addResult(CoupleResult result) {
        results.add(result);
        return this;
    }

    public WeekId getWeekId() {
        return weekId;
    }

    public int getYear() {
        return year;
    }

    public Show build() {
        return new Show(year, weekId, results.build());
    }
}
