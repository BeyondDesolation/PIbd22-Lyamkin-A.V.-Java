package com.utils;

public enum DetailsType {

    EMPTY,
    OLD_RADAR,
    NEW_RADAR,
    LINES;

    public static DetailsType fromNumeric(int n) {
        switch (n) {
            case 1:
                return OLD_RADAR;
            case 2:
                return NEW_RADAR;
            case 3:
                return LINES;
            default:
                return EMPTY;
        }
    }
}
