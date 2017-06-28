package com.andrefarzat.GP.logging;


import com.andrefarzat.GP.GP;
import com.andrefarzat.GP.Individual;

public interface Logger {
    void logPopulation(GP gp);
    void logCrossover(GP gp, Individual father, Individual mother, Individual neo);
    void logMutation(GP gp, Individual father, Individual neo);
}
