package ru.vsu.utils;

public class ToBooleanConverter {
    public static Boolean convert(Object o) {
        if (o instanceof Number) {
            return (Float) o != 0;
        } else if (o instanceof Boolean) {
            return (Boolean) o;
        } else {
            return o != null;
        }
    }
}
