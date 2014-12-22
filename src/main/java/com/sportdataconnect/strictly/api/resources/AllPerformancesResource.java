package com.sportdataconnect.strictly.api.resources;

import com.sportdataconnect.strictly.api.model.Series;
import com.sportdataconnect.strictly.api.response.FlattenedCoupleResult;
import com.sportdataconnect.strictly.api.response.Show;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Will on 02/12/2014.
 */
@Produces(MediaType.APPLICATION_JSON)
@Path("/allPerformances")
public class AllPerformancesResource {

    private List<FlattenedCoupleResult> results;

    public AllPerformancesResource(List<FlattenedCoupleResult> results) {
        this.results = results;
    }

    @GET
    public List<FlattenedCoupleResult> getAllPerformances(@QueryParam("sortBy") String sortBy) {
        if ("Scores".equals(sortBy)) {
            return FlattenedCoupleResult.SCORE_ORDERING.immutableSortedCopy(results);
        } else {
            return results;
        }
    }
}
