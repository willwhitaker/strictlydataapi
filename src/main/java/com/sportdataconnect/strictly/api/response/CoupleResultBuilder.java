package com.sportdataconnect.strictly.api.response;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class CoupleResultBuilder {
    private String coupleId;
    private String coupleName;
    private String dance;
    private String scores;
    private String outcome;
    private String music;

    public CoupleResultBuilder setCoupleId(String coupleId) {
        this.coupleId = coupleId;
        return this;
    }

    public CoupleResultBuilder setCoupleName(String coupleName) {
        this.coupleName = coupleName;
        return this;
    }

    public CoupleResultBuilder setDance(String dance) {
        this.dance = dance;
        return this;
    }

    public CoupleResultBuilder setScores(String scores) {
        this.scores = scores;
        return this;
    }

    public CoupleResultBuilder setOutcome(String outcome) {
        this.outcome = outcome;
        return this;
    }

    public CoupleResultBuilder setMusic(String music) {
        this.music = music;
        return this;
    }

    public CoupleResult createCoupleResult() {
        int total = 0;
        int numJudges = 0;
        try {
            List<Integer> scoreValues = extractIndividualScoreValues(scores);
            numJudges = scoreValues.size();
            for (Integer value : scoreValues) {
                total += value;
            }
        } catch (Exception e) {

        }
        return new CoupleResult(coupleId, coupleName, dance, scores, outcome, music, total, numJudges);
    }

    public static List<Integer> extractIndividualScoreValues(String scores) {
        ImmutableList.Builder<Integer> result = ImmutableList.builder();
        String trimmed = extractIndividualScores(scores).trim();
        String[] individualStrings = trimmed.split(",");
        for (String s : individualStrings) {
            int value = Integer.valueOf(s.trim());
            result.add(value);
        }
        return result.build();
    }

    private static String extractIndividualScores(String scores) {
        return scores.substring(scores.indexOf('(') + 1, scores.indexOf(')'));
    }
}