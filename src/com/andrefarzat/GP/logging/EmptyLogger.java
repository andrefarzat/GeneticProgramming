package com.andrefarzat.GP.logging;

import com.andrefarzat.GP.GP;
import com.andrefarzat.GP.Individual;

/**
 * Created by andrefarzat on 27/06/17.
 */
public class EmptyLogger implements Logger {
    @Override
    public void logPopulation(GP gp) {}

    @Override
    public void logCrossover(GP gp, Individual father, Individual mother, Individual neo) {}

    @Override
    public void logMutation(GP gp, Individual father, Individual neo) {}
}
