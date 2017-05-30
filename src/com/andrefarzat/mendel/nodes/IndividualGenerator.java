package com.andrefarzat.mendel.nodes;


import java.util.ArrayList;

public abstract class IndividualGenerator {
    public ArrayList<Function> generateInitialPopulation(int populationSize, int depth) {
        ArrayList<Function> population = new ArrayList<Function>();

        for(int i = 0; i < populationSize; i++) {
            population.add(this.generateFunction(depth));
        }

        return population;
    }

    public abstract Function generateFunction(int depth);
}
