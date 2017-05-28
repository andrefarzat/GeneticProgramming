package com.andrefarzat.mendel.nodes;


public abstract class IndividualGenerator {
    public Node[] generateInitialPopulation(int populationSize, int depth) {
        Node[] population = new Node[populationSize];

        for(int i = 0; i < populationSize; i++) {
            population[i] = this.generateFunction(depth);
        }

        return population;
    }

    public abstract Function generateFunction(int depth);
}
