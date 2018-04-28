package com.sportdataconnect.strictly.api.scraper;

import com.google.common.collect.ImmutableList;
import com.sportdataconnect.strictly.api.jarutil.JarUtil;
import com.sportdataconnect.strictly.api.response.FlattenedCoupleResult;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

public class ShowScraperTest {

    @Test
    public void test() throws Exception {
        BufferedReader lines = JarUtil.openReaderForResource("testSeries");
        ImmutableList.Builder<FlattenedCoupleResult> allResults = ImmutableList.builder();
        ShowScraper.scrapeLines(lines, 2003, allResults);

        for (FlattenedCoupleResult result : allResults.build()) {
            System.out.println(result);
        }
    }
}
