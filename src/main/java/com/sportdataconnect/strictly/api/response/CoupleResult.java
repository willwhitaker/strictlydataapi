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

    @Override
    public String toString() {
        return "CoupleResult{" +
                "coupleId='" + coupleId + '\'' +
                ", coupleName='" + coupleName + '\'' +
                ", dance='" + dance + '\'' +
                ", scores='" + scores + '\'' +
                ", outcome='" + outcome + '\'' +
                ", music='" + music + '\'' +
                ", total=" + total +
                ", numJudges=" + numJudges +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoupleResult that = (CoupleResult) o;

        if (total != that.total) return false;
        if (numJudges != that.numJudges) return false;
        if (!coupleId.equals(that.coupleId)) return false;
        if (!coupleName.equals(that.coupleName)) return false;
        if (!dance.equals(that.dance)) return false;
        if (!scores.equals(that.scores)) return false;
        if (outcome != null ? !outcome.equals(that.outcome) : that.outcome != null) return false;
        return music.equals(that.music);
    }

    @Override
    public int hashCode() {
        int result = coupleId.hashCode();
        result = 31 * result + coupleName.hashCode();
        result = 31 * result + dance.hashCode();
        result = 31 * result + scores.hashCode();
        result = 31 * result + (outcome != null ? outcome.hashCode() : 0);
        result = 31 * result + music.hashCode();
        result = 31 * result + total;
        result = 31 * result + numJudges;
        return result;
    }
}
