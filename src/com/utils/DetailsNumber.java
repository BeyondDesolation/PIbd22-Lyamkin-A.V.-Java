package com.utils;

public enum DetailsNumber {
    ONE,
    TWO,
    THREE,
    EMPTY;

    public static DetailsNumber fromNumeric(int n){
        switch (n){
            case 1:
                return ONE;
            case 2:
                return TWO;
            case 3:
                return THREE;
            default:
                return EMPTY;

        }
    }
}
