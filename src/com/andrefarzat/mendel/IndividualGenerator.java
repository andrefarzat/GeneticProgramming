package com.andrefarzat.mendel;


import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Population;
import com.andrefarzat.mendel.nodes.Function;


public abstract class IndividualGenerator {
    public Population generateInitialPopulation(int populationSize, int depth) {
        Population population = new Population();

        for(int i = 0; i < populationSize; i++) {
            population.add(this.generateIndividual(depth));
        }

        return population;
    }

    public Individual generateIndividual(int depth) {
        Individual ind = new Individual();
        ind.three = this.generateFunction(depth);
        return ind;
    }

    public abstract Function generateFunction(int depth);
}
