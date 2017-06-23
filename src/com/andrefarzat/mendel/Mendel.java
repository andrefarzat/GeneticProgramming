package com.andrefarzat.mendel;

import com.andrefarzat.mendel.logging.MendelLogger;
import com.andrefarzat.mendel.operators.CrossoverOperator;
import com.andrefarzat.mendel.operators.MutationOperator;
import com.andrefarzat.mendel.selectors.Selection;
import com.andrefarzat.mendel.selectors.Selector;

import java.util.ArrayList;


public abstract class Mendel {
    protected int generationNumber = 0;
    public abstract int getDepth();
    public abstract int getPopulationSize();

    public abstract MutationOperator[]  getMutationOperators();
    public abstract CrossoverOperator[] getCrossOperators();
    public abstract IndividualGenerator getGenerator();

    public abstract void evaluate(Individual individual);
    public abstract boolean shouldStop(Population population);

    public abstract MendelLogger getLogger();

    public abstract Selector getSelector();

    public MutationOperator getRandomMutationOperator() {
        MutationOperator[] operators = this.getMutationOperators();
        int index = Utils.random.nextInt(operators.length);
        return operators[index];
    }

    public CrossoverOperator getRandomCrossOperator() {
        CrossoverOperator[] operators = this.getCrossOperators();
        int index = Utils.random.nextInt(operators.length);
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
        ArrayList<Individual> neos = new ArrayList<>();

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
        this.getLogger().logPopulation(nextGeneration);

        // Ranking the current population by its fitness
        population.sortByFitness();

        // Calculating all the selection probability at once
        population.calculateProbability();

        int size = population.size();
        for (int i = 0; i < size; ) {
            Selection selection = this.getSelector().get(i, size, population);

            if (selection.getType() == Selection.Types.clone) {
                Individual neo = selection.getFather().clone();
                nextGeneration.add(neo);

                this.getLogger().logIndividual(nextGeneration, neo);
                this.getLogger().logClone(selection.getFather(), neo);

                i += 1;
            } else if (selection.getType() == Selection.Types.crossover) {
                ArrayList<Individual> neos = this.cross(selection.getFather(), selection.getMother());
                nextGeneration.addAll(neos);

                for(Individual neo : neos) {
                    this.getLogger().logIndividual(nextGeneration, neo);
                    this.getLogger().logCross(selection.getFather(), selection.getMother(), neo);

                    i += 1;
                }
            } else if (selection.getType() == Selection.Types.mutation) {
                Individual neo = this.mutate(selection.getFather());
                nextGeneration.add(neo);

                this.getLogger().logIndividual(nextGeneration, neo);
                this.getLogger().logMutation(selection.getFather(), neo);

                i += 1;
            } else if (selection.getType() == Selection.Types.discarded) {
                // The remaining is discarded and we create brand new ones
                Individual neo = this.getGenerator().generateIndividual(this.getDepth());
                nextGeneration.add(neo);
                this.getLogger().logIndividual(nextGeneration, neo);
                i += 1;
            } else {
                this.getLogger().log("Should never reach here");
            }
        }

        return nextGeneration;
    }

    public void run() {
        this.getLogger().logStartTime();

        // 1. Generate initial population
        Population population = this.getGenerator().generateInitialPopulation(this.getPopulationSize(), this.getDepth());
        population.setGenerationNumber(++this.generationNumber);
        this.getLogger().logInitialPopulation(population);

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
            population.setGenerationNumber(++this.generationNumber);
        }

        this.getLogger().logEndTime();
        System.out.println("Done!");
    }
}