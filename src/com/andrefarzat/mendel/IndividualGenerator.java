package com.andrefarzat.mendel;


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
        Individual ind = null;

        do {
            ind = new Individual();
            ind.setTree(this.generateFunction(depth));
        } while (! this.validateIndividual(ind));


        return ind;
    }

    public abstract boolean validateIndividual(Individual ind);

    public abstract Function generateFunction(int depth);
}
