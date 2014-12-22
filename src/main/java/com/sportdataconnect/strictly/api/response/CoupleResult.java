package com.sportdataconnect.strictly.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Will on 28/11/2014.
 */
public class CoupleResult {

    private String coupleId;
    private String coupleName;
    private String dance;
    private String scores;
    private String outcome;
    private String music;
    private int total;
    private int numJudges;

    public CoupleResult(String coupleId, String coupleName, String dance, String scores, String outcome, String music, int total, int numJudges) {
        this.coupleId = coupleId;
        this.coupleName = coupleName;
        this.dance = dance;
        this.scores = scores;
        this.outcome = outcome;
        this.music = music;
        this.total = total;
        this.numJudges = numJudges;
    }

    public String getCoupleId() {
        return coupleId;
    }

    @JsonProperty
    public String getCoupleName() {
        return coupleName;
    }

    @JsonProperty
    public String getDance() {
        return dance;
    }

    @JsonProperty
    public String getScores() {
        return scores;
    }

    @JsonProperty
    public String getOutcome() {
        return outcome;
    }

    @JsonProperty
    public String getMusic() {
        return music;
    }

    @JsonProperty
    public int getTotal() {
        return total;
    }

    @JsonProperty
    public int getNumJudges() {
        return numJudges;
    }

    @JsonProperty
    public double getAverageScore() {
        if (numJudges > 0) {
            return (double) total / numJudges;
        } else {
            return 0;
        }
    }
}
