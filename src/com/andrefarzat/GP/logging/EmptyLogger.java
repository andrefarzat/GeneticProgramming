package com.andrefarzat.GP.logging;

import com.andrefarzat.GP.GP;
import com.andrefarzat.GP.Individual;


public class EmptyLogger implements Logger {
    @Override
    public void logStart(GP gp) {}

    @Override
    public void logEnd(GP gp) {}

    @Override
    public void logPopulation(GP gp) {}

    @Override
    public void logCrossover(GP gp, Individual father, Individual mother, Individual neo) {}

    @Override
    public void logMutation(GP gp, Individual father, Individual neo) {}
}
