package com.sportdataconnect.strictly.api.resources;

import com.sportdataconnect.strictly.api.model.Series;
import com.sportdataconnect.strictly.api.response.Show;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Will on 28/11/2014.
 */
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class OverviewResource {

    private Map<Integer, Series> allSeries;

    public OverviewResource(Map<Integer, Series> allSeries) {
        this.allSeries = allSeries;
    }

    @GET
    @Path("series/{year}")
    public List<Show> getAllShowsInYear(@PathParam("year") int year) {
        Series series = allSeries.get(year);
        if (series != null)
            return series.getAllShowResponses();
        else
            throw new WebApplicationException(404);
    }
}
