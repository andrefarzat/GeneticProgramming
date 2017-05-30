package com.andrefarzat.mendel;

import java.util.ArrayList;
import java.util.Random;


public class Utils {
    public static <T> T getFromListRandomly(ArrayList<T> list) {
        Random random = new Random();
        int size = list.size();

        if (size == 0) {
            // TODO: Throws an exception
            return null;
        }

        int index = random.nextInt(size);
        return list.get(index);
    }
}
