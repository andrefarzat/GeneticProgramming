package com.andrefarzat.mendel.logging;

import com.andrefarzat.mendel.Population;


public interface MendelLogger {
    void logStartTime();
    void logEndTime();

    void logPopulation(String label, Population population);
}
