package com.andrefarzat.mendel;

import com.andrefarzat.mendel.operators.CrossoverOperator;
import com.andrefarzat.mendel.operators.MutationOperator;

import java.util.ArrayList;
import java.util.Random;


public abstract class Mendel {
    public abstract int getDepth();
    public abstract int getPopulationSize();

    public abstract MutationOperator[]  getMutationOperators();
    public abstract CrossoverOperator[] getCrossOperators();
    public abstract IndividualGenerator getGenerator();

    public abstract void evaluate(Individual individual);
    public abstract boolean shouldStop(Population population);

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

    public MutationOperator getRandomMutationOperator() {
        MutationOperator[] operators = this.getMutationOperators();
        int index = this.random.nextInt(operators.length);
        return operators[index];
    }

    public CrossoverOperator getRandomCrossOperator() {
        CrossoverOperator[] operators = this.getCrossOperators();
        int index = this.random.nextInt(operators.length);
        return operators[index];
    }

    public Individual mutate(Individual individual) {
        MutationOperator operator = this.getRandomMutationOperator();

        int i = 5;
        while (i > 0) {
            // We try five times. If we can't have a good x-man after that, we create a brand new
            Individual neo = operator.mutate(this, individual);
            i--;

            if (this.getGenerator().validateIndividual(neo)) {
                return neo;
            }
        }

        return this.getGenerator().generateIndividual(this.getDepth());
    }

    public ArrayList<Individual> cross(Individual indA, Individual indB) {
        CrossoverOperator operator = this.getRandomCrossOperator();
        ArrayList<Individual> neos = new ArrayList<Individual>();

        int i = 5;
        while (i > 0) {
            // We try five times. If we can't have two good x-men after that, we create new ones
            neos = operator.cross(this, indA, indB);
            i--;

            boolean allAreGood = true;
            for(Individual ind : neos) {
                if (!this.getGenerator().validateIndividual(ind)) {
                    allAreGood = false;
                }
            }

            if (allAreGood) {
                return neos;
            }

            neos.clear();
        }

        // In case we don't have good candidates, we generate new ones
        neos.clear();
        neos.add(this.getGenerator().generateIndividual(this.getDepth()));
        neos.add(this.getGenerator().generateIndividual(this.getDepth()));
        return neos;
    }

    public Population mutatePopulation(Population population) {
        Population nextGeneration = new Population();

        // Ranking the current population by its measure
        population.sortByMeasure();

        int size = population.size();
        for(int i = 0; i < size; ) {
            if(i == 0) {
                // The top 3% goes to maralto
                nextGeneration.add(population.get(i).clone());

                i += 1;
            } else if(i < (size * 0.8)) {
                // 1. Getting the best two candidates
                Individual indA = population.get(i);
                Individual indB = population.get(i + 1);

                // 2. Crossing them
                nextGeneration.addAll(this.cross(indA, indB));

                // 3. Moving forward
                i += 2;
            } else if (i < (size * 0.9)) {
                // Mutating
                Individual ind = population.get(i);
                nextGeneration.add(this.mutate(ind));
                i += 1;
            } else {
                // The remaining is discarded and we create brand new ones
                nextGeneration.add(this.getGenerator().generateIndividual(this.getDepth()));
                i += 1;
            }
        }

        return nextGeneration;
    }

    public void run() {
        // 1. Generate initial population
        Population population = this.getGenerator().generateInitialPopulation(this.getPopulationSize(), this.getDepth());

        while(true) {
            // 2. Evaluate all population
            for(Individual individual : population.getAll()) {
                this.evaluate(individual);
            }

            // 3. Run Terminator
            if (this.shouldStop(population)) {
                break;
            }

            // 4. We mutate !
            population = this.mutatePopulation(population);
        }

        System.out.println("Done!");
    }
}