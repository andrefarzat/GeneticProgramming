package com.andrefarzat.mendel;

import java.util.Random;

import com.andrefarzat.mendel.operators.GeneticOperator;


public abstract class Mendel {
    public Population currentPopulation;

    public abstract int getDepth();
    public abstract int getPopulationSize();

    public abstract GeneticOperator[] getMutationOperators();
    public abstract GeneticOperator[] getCrossOperators();
    public abstract IndividualGenerator getGenerator();

    public abstract void evaluate(Individual individual);
    public abstract boolean shouldStop();

    protected final Random random = new Random();

    public int getLogLevel() {
        return 5;
    }

    public void log(int level, String msg) {
        if (this.getLogLevel() >= level) {
            System.out.println(msg);
        }
    }

    public void log(int level, String msg, Object ...params) {
        msg = String.format(msg, params);
        this.log(level, msg);
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
        Population nextGeneration = new Population();

        // Ranking the current population by its measure
        population.sortByMeasure();

        int size = population.size();
        for(int i = 0; i < size; i++) {
            Individual individual = population.getAndRemove(0);
            Individual neo = null;
            if (individual == null) break;

            if (i < (size * 0.00)) {
                // These are the untouchable ones! They simply go to the next generation
                neo = individual.clone();
            } else if (i < (size * 0.17)) {
                // Here we mutate !
                GeneticOperator operator = this.getRandomMutationOperator();
                neo = operator.create(this, individual, population);
            } else if (i < (size * 0.8)) {
                // Almost everyone will cross
                GeneticOperator operator = this.getRandomCrossOperator();
                neo = operator.create(this, individual, population);
            } else {
                // The remaining is discarded and we create brand new ones
                neo = this.getGenerator().generateIndividual(this.getDepth());
            }

            if (!this.getGenerator().validateIndividual(neo)) {
                i --;
                population.add(0, individual);
                continue;
            }

            nextGeneration.add(neo);
        }

        return nextGeneration;
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