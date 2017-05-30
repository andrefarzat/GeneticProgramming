package com.andrefarzat.mendel.operators;


import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Mendel;

public class CreatorOperator extends GeneticOperator {

    public Individual create(Mendel mendel, Individual individual) {
        return  mendel.getGenerator().generateIndividual(mendel.getDepth());
    }
}
