package com.andrefarzat.mendel.logging;

import com.andrefarzat.mendel.Population;


public interface MendelLogger {
    void logInitialTime();
    void logFinishTime();

    void logPopulation(String label, Population population);
}
