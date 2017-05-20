package com.andrefarzat.mendel.operators;

import com.andrefarzat.mendel.individuals.Function;
import com.andrefarzat.mendel.individuals.Individual;


public abstract class MutationOperator {

    public abstract void mutate(Individual individual);

}
