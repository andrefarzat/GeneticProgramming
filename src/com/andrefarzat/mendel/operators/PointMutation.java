package com.andrefarzat.mendel.operators;

import com.andrefarzat.mendel.individuals.Function;
import com.andrefarzat.mendel.individuals.Individual;
import com.andrefarzat.mendel.individuals.Terminal;


public class PointMutation extends MutationOperator {

    public void mutate(Individual individual) {
        individual.mutate();
    }

}
