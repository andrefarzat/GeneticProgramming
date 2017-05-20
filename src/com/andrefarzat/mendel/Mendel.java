package com.andrefarzat.mendel;

import com.andrefarzat.mendel.individuals.Individual;
import com.andrefarzat.mendel.individuals.IndividualGenerator;
import com.andrefarzat.mendel.operators.MutationOperator;

import java.util.ArrayList;
import java.util.Random;


public class Mendel {
    protected int depth          = 3;
    protected int populationSize = 1000;

    protected IFitEvaluator       fitEvaluator;
    protected ITerminator         terminator;
    protected IndividualGenerator generator;
    protected Individual[]        currentPopulation;

    protected ArrayList<MutationOperator> mutationOperators = new ArrayList<MutationOperator>();

    public void addMutationOperator(MutationOperator operator) {
        this.mutationOperators.add(operator);
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setFitEvaluator(IFitEvaluator fitEvaluator) {
        this.fitEvaluator = fitEvaluator;
    }

    public void setTerminator(ITerminator terminator) {
        this.terminator = terminator;
    }

    public void setGenerator(IndividualGenerator generator) {
        this.generator = generator;
    }

    public void setPopulationSize(int size) {
        this.populationSize = size;
    }

    protected <T> T getRandomFromList(ArrayList<T> list) {
        int index = (new Random()).nextInt(list.size()) - 1;
        if (index < 0) index = 0;
        return list.get(index);
    }


    public Individual[] mutateCurrentPopulation() {
        Individual[] newGeneration = new Individual[this.populationSize];

        for(Individual individual : this.currentPopulation) {
            MutationOperator operator = this.getRandomFromList(this.mutationOperators);
            operator.mutate(individual);
        }

        return newGeneration;
    }

    public void run() {
        // 1. Generate initial population
        this.currentPopulation = this.generator.generateInitialPopulation(this.populationSize, this.depth);

        while(true) {
            // 2. Evaluate all population
            for (Individual individual : this.currentPopulation) {
                this.fitEvaluator.evaluate(individual);
            }

            // 3. Run Terminator
            if (this.terminator.shouldStop(this)) {
                break;
            }

            // 4. We mutate !
            this.currentPopulation = this.mutateCurrentPopulation();
        }

        System.out.println("Done!");
    }
}