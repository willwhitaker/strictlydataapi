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
        if (line.isEmpty() ||
                line.startsWith("Couple") ||
                line.startsWith("Running order") ||
                line.startsWith("Live from the") ||
                line.startsWith("This week's show is") ||
                line.startsWith("Musical guest")) {
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
                String[] tokens = line.split("\t");
                CoupleResult result;
                if (tokens.length == 3) {
                    result = new CoupleResultBuilder()
                            .setCoupleId("null")
                            .setCoupleName("???")
                            .setScores(tokens[0])
                            .setDance(tokens[1])
                            .setMusic(tokens[2])
                            .createCoupleResult();
                } else if (year == 2004) {
                    result = new CoupleResultBuilder()
                            .setCoupleId("null")
                            .setCoupleName(tokens[0])
                            .setDance(tokens[1])
                            .setMusic(tokens[2])
                            .setScores(tokens[1])
                            .setOutcome(tokens.length > 4 ? tokens[4] : "n/a")
                            .createCoupleResult();
                } else {
                    result = new CoupleResultBuilder()
                            .setCoupleId("null")
                            .setCoupleName(tokens[0])
                            .setScores(tokens[1])
                            .setDance(tokens[2])
                            .setMusic(tokens[3])
                            .setOutcome(tokens.length > 4 ? tokens[4] : "n/a")
                            .createCoupleResult();
                }
                flattenCouplePerformanceRecord(currentShow, result);
                currentShow.addResult(result);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Skipping " + line);
            }
        }
    }

    private void flattenCouplePerformanceRecord(ShowBuilder show, CoupleResult result) {
        flattenedCoupleResults.add(new FlattenedCoupleResult(show.getYear(), show.getWeekId(), result));
    }
}
