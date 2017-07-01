package com.andrefarzat.GP;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;


public class Utils {
    public static int currentIndividualId = 0;
    public static Random random = new Random();

    public static <T> T getFromListRandomly(List<T> list) {
        int size = list.size();

        if (size == 0) {
            // TODO: Throws an exception
            return null;
        }

        int index = random.nextInt(size);
        return list.get(index);
    }

    public static int compareDouble(double one, double two) {
        double diff = one - two;
        if (Math.abs(diff) < 0.01) {
            return 0; // equals
        }

        return one > two ? 1 : -1;
    }

    public static double fixDouble(String value) {
        return Double.parseDouble(value);
    }

    public static double fixDouble(double value) {
        return Utils.fixDouble(String.format("%.2f", value));
    }

    public static int nextIndividualId() {
        return ++ Utils.currentIndividualId;
    }
}