package com.andrefarzat.mendel;

import com.andrefarzat.mendel.individuals.Function;
import com.andrefarzat.mendel.individuals.Individual;
import com.andrefarzat.mendel.individuals.IndividualGenerator;
import com.andrefarzat.mendel.individuals.Terminal;
import com.andrefarzat.mendel.operators.MutationOperator;

import java.util.ArrayList;
import java.util.Random;


public class Mendel {
    protected int depth          = 3;
    protected int populationSize = 1000;

    protected FitEvaluator        fitEvaluator;
    protected Terminator          terminator;
    protected IndividualGenerator generator;
    protected Individual[]        currentPopulation;

    protected ArrayList<MutationOperator> mutationOperators = new ArrayList<MutationOperator>();

    public void addMudationOperator(MutationOperator operator) {
        this.mutationOperators.add(operator);
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setFitEvaluator(FitEvaluator fitEvaluator) {
        this.fitEvaluator = fitEvaluator;
    }

    public void setTerminator(Terminator terminator) {
        this.terminator = terminator;
    }

    public void setGenerator(IndividualGenerator generator) {
        this.generator = generator;
    }

    public void setPopulationSize(int size) {
        this.populationSize = size;
    }

    public void run() {
        // 1. Generate initial population
        this.currentPopulation = this.generator.generateInitialPopulation(this.populationSize, this.depth);
        // 2. Fit all population
        // 3. Run Terminator
        // 4. Back to 2.
    }
}