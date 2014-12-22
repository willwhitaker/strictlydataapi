package com.sportdataconnect.strictly.api;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Ordering;
import com.sportdataconnect.strictly.api.model.Series;
import com.sportdataconnect.strictly.api.resources.AllPerformancesResource;
import com.sportdataconnect.strictly.api.resources.OverviewResource;
import com.sportdataconnect.strictly.api.response.FlattenedCoupleResult;
import com.sportdataconnect.strictly.api.response.Show;
import com.sportdataconnect.strictly.api.scraper.ShowScraper;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import static com.sportdataconnect.strictly.api.scraper.ShowScraper.scrapeLines;

/**
 * Created by Will on 28/11/2014.
 */
public class StrictlyApiApplication extends Application<StrictlyApiConfiguration> {
    public static void main(String[] args) throws Exception {
        new StrictlyApiApplication().run(args);
    }

    @Override
    public String getName() {
        return "strictly-api";
    }

    @Override
    public void initialize(Bootstrap<StrictlyApiConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle());
    }

    @Override
    public void run(StrictlyApiConfiguration configuration,
                    Environment environment) {
        System.out.println(Charset.defaultCharset().name());
        try {
            ImmutableMap.Builder<Integer, Series> seriesBuilder = ImmutableMap.builder();
            ImmutableList.Builder<FlattenedCoupleResult> flattenedCoupleResults = ImmutableList.builder();
            try (BufferedReader reader = openReaderForResource("series1")) {
                seriesBuilder.put(2003, new Series(scrapeLines(reader, 2003, flattenedCoupleResults)));
            }
            try (BufferedReader reader = openReaderForResource("series2")) {
                seriesBuilder.put(2004, new Series(scrapeLines(reader, 2004, flattenedCoupleResults)));
            }
            try (BufferedReader reader = openReaderForResource("series3")) {
                seriesBuilder.put(2005, new Series(scrapeLines(reader, 2005, flattenedCoupleResults)));
            }
            try (BufferedReader reader = openReaderForResource("series4")) {
                seriesBuilder.put(2006, new Series(scrapeLines(reader, 2006, flattenedCoupleResults)));
            }
            try (BufferedReader reader = openReaderForResource("series5")) {
                seriesBuilder.put(2007, new Series(scrapeLines(reader, 2007, flattenedCoupleResults)));
            }
            try (BufferedReader reader = openReaderForResource("series6")) {
                seriesBuilder.put(2008, new Series(scrapeLines(reader, 2008, flattenedCoupleResults)));
            }
            try (BufferedReader reader = openReaderForResource("series7")) {
                seriesBuilder.put(2009, new Series(scrapeLines(reader, 2009, flattenedCoupleResults)));
            }
            try (BufferedReader reader = openReaderForResource("series8")) {
                seriesBuilder.put(2010, new Series(scrapeLines(reader, 2010, flattenedCoupleResults)));
            }
            try (BufferedReader reader = openReaderForResource("series9")) {
                seriesBuilder.put(2011, new Series(scrapeLines(reader, 2011, flattenedCoupleResults)));
            }
            try (BufferedReader reader = openReaderForResource("series10")) {
                seriesBuilder.put(2012, new Series(scrapeLines(reader, 2012, flattenedCoupleResults)));
            }
            try (BufferedReader reader = openReaderForResource("series11")) {
                seriesBuilder.put(2013, new Series(scrapeLines(reader, 2013, flattenedCoupleResults)));
            }
            try (BufferedReader reader = openReaderForResource("series12")) {
                seriesBuilder.put(2014, new Series(scrapeLines(reader, 2014, flattenedCoupleResults)));
            }
            environment.jersey().register(new OverviewResource(seriesBuilder.build()));
            ImmutableList<FlattenedCoupleResult> flattenedCoupleList = flattenedCoupleResults.build();
            flattenedCoupleList = FlattenedCoupleResult.CHRONOLOGICAL_ORDERING.immutableSortedCopy(flattenedCoupleList);
            environment.jersey().register(new AllPerformancesResource(flattenedCoupleList));
        } catch (IOException e) {
            throw new RuntimeException("Unable to load input data", e);
        }
    }

    private BufferedReader openReaderForResource(String resourceName) {
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/com/sportdataconnect/strictly/api/" + resourceName);
        return new BufferedReader(new InputStreamReader(resourceAsStream, Charset.forName("UTF-8")));
    }
}
