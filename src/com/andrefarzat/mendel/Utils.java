package com.andrefarzat.mendel;

import java.util.ArrayList;
import java.util.Random;


public class Utils {
    public static Random random = new Random();
    public static <T> T getFromListRandomly(ArrayList<T> list) {
        int size = list.size();

        if (size == 0) {
            // TODO: Throws an exception
            return null;
        }

        int index = random.nextInt(size);
        return list.get(index);
    }

    public static double formatDouble(Double value) {
        return Double.parseDouble(String.format("%.1f", value));
    }
}
