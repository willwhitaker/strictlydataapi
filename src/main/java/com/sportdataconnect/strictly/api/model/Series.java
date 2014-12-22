package com.sportdataconnect.strictly.api.model;

import com.sportdataconnect.strictly.api.response.Show;

import java.util.List;

/**
 * Created by Will on 28/11/2014.
 */
public class Series {

    private List<Show> allShowResponses;

    public Series(List<Show> allShowResponses) {
        this.allShowResponses = allShowResponses;
    }

    public List<Show> getAllShowResponses() {
        return allShowResponses;
    }
}
