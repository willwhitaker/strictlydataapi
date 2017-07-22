package com.sportdataconnect.strictly.api.scraper;

import com.google.common.collect.ImmutableList;
import com.sportdataconnect.strictly.api.model.WeekId;
import com.sportdataconnect.strictly.api.response.CoupleResult;
import com.sportdataconnect.strictly.api.response.CoupleResultBuilder;
import com.sportdataconnect.strictly.api.response.FlattenedCoupleResult;
import com.sportdataconnect.strictly.api.response.Show;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
/**
 * Created by Will on 28/11/2014.
 */
public class ShowScraper {

    private ImmutableList.Builder<Show> loadedShows;
    private ShowBuilder currentShow;
    private ImmutableList.Builder<FlattenedCoupleResult> flattenedCoupleResults;

    public static List<Show> scrapeLines(BufferedReader lines, int year, ImmutableList.Builder<FlattenedCoupleResult> flattenedCoupleResults) throws IOException {
        return new ShowScraper().doScrapeLines(lines, year, flattenedCoupleResults);
    }

    private List<Show> doScrapeLines(BufferedReader lines, int year, ImmutableList.Builder<FlattenedCoupleResult> flattenedCoupleResults) throws IOException {
        this.flattenedCoupleResults = flattenedCoupleResults;
        loadedShows = ImmutableList.builder();
        String line = lines.readLine();
        while (line != null) {
            processLine(line, year);
            line = lines.readLine();
        }
        if (currentShow != null) {
            loadedShows.add(currentShow.build());
        }
        return loadedShows.build();
    }

    private void processLine(String line, int year) {
        if (line.isEmpty() || line.startsWith("Couple") || line.startsWith("Running order")) {
            return;
        }
        WeekId weekId = WeekId.fromString(year, line);
        if (weekId != null) {
            if (currentShow != null) {
                loadedShows.add(currentShow.build());
            }
            currentShow = new ShowBuilder(weekId)
                    .setYear(year);
        } else {
            try {
                StringTokenizer tokens = new StringTokenizer(line, "\t");
                CoupleResult result;
                if (year == 2004) {
                    result = new CoupleResultBuilder()
                            .setCoupleId("null")
                            .setCoupleName(tokens.nextToken())
                            .setDance(tokens.nextToken())
                            .setMusic(tokens.nextToken())
                            .setScores(tokens.nextToken())
                            .setOutcome(tokens.hasMoreTokens() ? tokens.nextToken() : "n/a")
                            .createCoupleResult();
                } else {
                    result = new CoupleResultBuilder()
                            .setCoupleId("null")
                            .setCoupleName(tokens.nextToken())
                            .setScores(tokens.nextToken())
                            .setDance(tokens.nextToken())
                            .setMusic(tokens.nextToken())
                            .setOutcome(tokens.hasMoreTokens() ? tokens.nextToken() : "n/a")
                            .createCoupleResult();
                }
                flattenCouplePerformanceRecord(currentShow, result);
                currentShow.addResult(result);
            } catch (NoSuchElementException e) {
                System.out.println("Skipping " + line);
            }
        }
    }

    private void flattenCouplePerformanceRecord(ShowBuilder show, CoupleResult result) {
        flattenedCoupleResults.add(new FlattenedCoupleResult(show.getYear(), show.getWeekId(), result));
    }
}
