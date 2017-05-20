package com.andrefarzat.mendel.individuals;


public abstract class IndividualGenerator {
    public Individual[] generateInitialPopulation(int populationSize, int depth) {
        Individual[] population = new Individual[populationSize];

        for(int i = 0; i < populationSize; i++) {
            population[i] = this.generateFunction(depth);
        }

        return population;
    }

    public abstract Function generateFunction(int depth);
}
