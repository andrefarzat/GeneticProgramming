package com.andrefarzat.mendel.operators;

import com.andrefarzat.mendel.Individual;

import java.util.ArrayList;
import java.util.Random;


public abstract class CrossOperator {
    public abstract Individual cross(Individual indA, Individual indB);

    public <T> T getFromListRandomly(ArrayList<T> list) {
        Random random = new Random();
        int index = random.nextInt(list.size() - 1);
        return list.get(index);
    }
}
