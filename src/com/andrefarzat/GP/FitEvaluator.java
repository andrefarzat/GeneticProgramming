package com.andrefarzat.GP;


import com.andrefarzat.mendel.IFitEvaluator;
import com.andrefarzat.mendel.individuals.Individual;

public class FitEvaluator implements IFitEvaluator {

    public void evaluate(Individual individual, float defaultValue) {
        float value = individual.getValue(defaultValue);
    }

}
