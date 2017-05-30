package com.andrefarzat.mendel;

import java.util.Random;

import com.andrefarzat.mendel.operators.GeneticOperator;


public abstract class Mendel {
    public Population currentPopulation;

    public abstract int getDepth();
    public abstract int getPopulationSize();

    public abstract GeneticOperator[] getCreatorOperators();
    public abstract GeneticOperator[] getMutationOperators();
    public abstract GeneticOperator[] getCrossOperators();
    public abstract IndividualGenerator getGenerator();

    public abstract void evaluate(Individual individual);
    public abstract boolean shouldStop();

    protected final Random random = new Random();

    public void log(String msg) {
        System.out.println(msg);
    }

    public void log(String msg, Object ...params) {
        msg = String.format(msg, params);
        this.log(msg);
    }

    public GeneticOperator getRandomCreatorOperator() {
        GeneticOperator[] operators = this.getCreatorOperators();
        int index = this.random.nextInt(operators.length);
        return operators[index];
    }

    public GeneticOperator getRandomMutationOperator() {
        GeneticOperator[] operators = this.getMutationOperators();
        int index = this.random.nextInt(operators.length);
        return operators[index];
    }

    public GeneticOperator getRandomCrossOperator() {
        GeneticOperator[] operators = this.getCrossOperators();
        int index = this.random.nextInt(operators.length);
        return operators[index];
    }

    public Population mutateCurrentPopulation() {
        Population newGeneration = new Population();
        newGeneration.concat(this.mutatePopulation(this.currentPopulation));

        return newGeneration;
    }

    public Population mutatePopulation(Population population) {
        Population newGeneration = new Population();
        Population currentPopulation = population.clone();

        // 1. Ranking the current population by its measure
        currentPopulation.sortByMeasure();

        // 2. We want only the top 25%
        int size = population.size();
        currentPopulation = currentPopulation.slice(0, size/4);

        double howManyWillBeMutated = Math.ceil((size * 20.0) / 100.0);
        double howManyWillBeCreated = Math.ceil((size * 10.0) / 100.0);

        for(int i = 0; i < 4; i ++) {
            for(Individual individual : currentPopulation.getAll()) {
                GeneticOperator operator;

                if (howManyWillBeCreated > 0) {
                    operator = this.getRandomCreatorOperator();
                    howManyWillBeCreated -= 1;
                } else if (howManyWillBeMutated > 0) {
                    operator = this.getRandomMutationOperator();
                    howManyWillBeMutated -= 1;
                } else {
                    operator = this.getRandomCrossOperator();
                }

                individual = operator.create(this, individual);
                newGeneration.add(individual);
            }
        }

        return newGeneration;
    }

    public void run() {
        // 1. Generate initial population
        this.currentPopulation = this.getGenerator().generateInitialPopulation(this.getPopulationSize(), this.getDepth());

        while(true) {
            // 2. Evaluate all population
            for(Individual individual : this.currentPopulation.getAll()) {
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