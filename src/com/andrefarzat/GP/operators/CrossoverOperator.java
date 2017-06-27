package com.andrefarzat.GP.operators;

import com.andrefarzat.GP.Individual;

/**
 * Created by andrefarzat on 2017-06-26.
 */
public interface CrossoverOperator {
    public Individual cross(Individual father, Individual mother);
}
