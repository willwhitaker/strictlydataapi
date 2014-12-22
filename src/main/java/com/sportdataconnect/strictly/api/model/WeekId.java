package com.sportdataconnect.strictly.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Ordering;

/**
 * Created by Will on 08/12/2014.
 */
public class WeekId {
    private int year;
    private Integer weekNumber;
    private String notes;

    public static int compare(WeekId x, WeekId y) {
        if (x.year == y.getYear()) {
            return Integer.compare(x.weekNumber, y.weekNumber);
        } else {
            return Integer.compare(x.year, y.year);
        }
    }

    public static WeekId fromString(int year, String string) {
        if (string.startsWith("Week ")) {
            try {
                String numberAndDescription = string.substring(5, string.length());
                String number = extractOpeningNumbers(numberAndDescription);
                String description = null;
                if (number.length() < numberAndDescription.length()) {
                    description = numberAndDescription.substring(number.length(), numberAndDescription.length());
                }
                return new WeekId(year, Integer.parseInt(number), description);
            } catch(Exception ex) {
                return null;
            }
        } else {
            return null;
        }
    }

    private static final ImmutableSet<Character> NUMERIC_CHARS = ImmutableSet.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    private static String extractOpeningNumbers(String numberAndDescription) {
        StringBuilder sb = new StringBuilder();
        for (char c : numberAndDescription.toCharArray()) {
            if (NUMERIC_CHARS.contains(c)) {
                sb.append(c);
            } else {
                return sb.toString();
            }
        }
        return sb.toString();
    }

    public WeekId(int year, Integer weekNumber, String notes) {
        this.year = year;
        this.weekNumber = weekNumber;
        this.notes = notes;
    }

    @JsonProperty
    public int getYear() {
        return year;
    }

    @JsonProperty
    public Integer getWeekNumber() {
        return weekNumber;
    }

    @JsonProperty
    public String getNotes() {
        return notes;
    }
}
