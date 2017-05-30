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
        int size = this.getPopulationSize();
        // 1. Ranking the current population by its measure
        this.currentPopulation.sortByMeasure();

        // 2. Getting only the half good
        Population halfGeneration = new Population();
        for(int i = 0; i < size / 2; i++) {
            halfGeneration.add(this.currentPopulation.get(i));
        }

        // 3. Generating a muted generation
        Population newGeneration = new Population();
        newGeneration.concat(halfGeneration);
        newGeneration.concat(this.mutatePopulation(halfGeneration));

        return newGeneration;
    }

    public Population mutatePopulation(Population population) {
        Population currentPopulation = population.clone();
        Population newGeneration = new Population();

        int size = population.size();
        double howManyWillBeMutated = Math.ceil((size * 80.0) / 100.0);
        double howManyWillBeCreated = Math.ceil((size * 10.0) / 100.0);

        //this.log(String.format("Size: %s; Mutated: %s; Created: %s", size, howManyWillBeMutated, howManyWillBeCreated));

        while (true) {
            Individual individual = currentPopulation.getRandomIndividualAndRemoveIt();

            if (individual == null) {
                break;
            }

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