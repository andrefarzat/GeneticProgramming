package com.andrefarzat.mendel.logging;

import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Population;


public interface MendelLogger {
    void logStartTime();

    void logEndTime();

    void logPopulation(Population population);

    void logIndividual(Population population, Individual individual);

    void logInitialPopulation(Population population);

    void logClone(Individual ind, Individual neo);

    void logCross(Individual indA, Individual indB, Individual neo);

    void logMutation(Individual ind, Individual neo);

    void log(String msg);

    void log(String msg, Object ...params);

}
