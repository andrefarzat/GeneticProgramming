package com.andrefarzat.mendel.operators;


import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Mendel;
import com.andrefarzat.mendel.Population;

public class CreatorOperator extends GeneticOperator {

    public Individual create(Mendel mendel, Individual individual, Population population) {
        return  mendel.getGenerator().generateIndividual(mendel.getDepth());
    }
}
