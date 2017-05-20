package com.andrefarzat.mendel;

import com.andrefarzat.mendel.individuals.Individual;
import com.andrefarzat.mendel.individuals.IndividualGenerator;
import com.andrefarzat.mendel.operators.MutationOperator;


public abstract class Mendel {
    public Individual[] currentPopulation;

    public abstract int getDepth();
    public abstract int getPopulationSize();

    public abstract MutationOperator[] getMutationOperators();
    public abstract IndividualGenerator getGenerator();

    public abstract void evaluate(Individual individual);
    public abstract boolean shouldStop();

    public Individual[] mutateCurrentPopulation() {
        Individual[] newGeneration = new Individual[this.getPopulationSize()];

        for(Individual individual : this.currentPopulation) {
            MutationOperator operator = this.getMutationOperators()[0];
            operator.mutate(individual);
        }

        return newGeneration;
    }

    public void run() {
        // 1. Generate initial population
        this.currentPopulation = this.getGenerator().generateInitialPopulation(this.getPopulationSize(), this.getDepth());

        while(true) {
            // 2. Evaluate all population
            for (Individual individual : this.currentPopulation) {
                this.evaluate(individual);
            }

            // 3. Run Terminator
            if (this.shouldStop()) {
                break;
            }

            // 4. We mutate !
            this.currentPopulation = this.mutateCurrentPopulation();
        }

        System.out.println("Done!");
    }
}