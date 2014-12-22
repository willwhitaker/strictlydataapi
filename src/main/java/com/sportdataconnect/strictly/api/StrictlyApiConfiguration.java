package com.sportdataconnect.strictly.api;

import io.dropwizard.Configuration;

/**
 * Created by Will on 28/11/2014.
 */
public class StrictlyApiConfiguration extends Configuration {

    private String x;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }
}
