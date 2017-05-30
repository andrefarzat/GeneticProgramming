package com.andrefarzat.mendel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.andrefarzat.mendel.nodes.Node;
import com.andrefarzat.mendel.nodes.Function;
import com.andrefarzat.mendel.nodes.IndividualGenerator;
import com.andrefarzat.mendel.operators.CrossOperator;
import com.andrefarzat.mendel.operators.MutationOperator;


public abstract class Mendel {
    public ArrayList<Function> currentPopulation;

    public abstract int getDepth();
    public abstract int getPopulationSize();

    public abstract MutationOperator[] getMutationOperators();
    public abstract CrossOperator[] getCrossOperators();
    public abstract IndividualGenerator getGenerator();

    public abstract void evaluate(Node node);
    public abstract boolean shouldStop();

    protected final Random random = new Random();

    public void log(String msg) {
        System.out.println(msg);
    }

    public void log(String msg, Object ...params) {
        msg = String.format(msg, params);
        this.log(msg);
    }

    public MutationOperator getRandomMutationOperator() {
        MutationOperator[] operators = this.getMutationOperators();
        int index = this.random.nextInt(operators.length);
        return operators[index];
    }

    public CrossOperator getRandomCrossOperator() {
        CrossOperator[] operators = this.getCrossOperators();
        int index = this.random.nextInt(operators.length);
        return operators[index];
    }

    public ArrayList<Function> mutateCurrentPopulation() {
        int size = this.getPopulationSize();
        // 1. Ranking the current population by its measure
        Collections.sort(this.currentPopulation);

        // 2. Getting only the half good
        ArrayList<Function> newGeneration = new ArrayList<Function>();
        for(int i = 0; i < size / 2; i++) {
            newGeneration.add(this.currentPopulation.get(i));
        }

        // 3. Generating a muted generation
        newGeneration.addAll(this.mutatePopulation(newGeneration));

        return newGeneration;
    }

    public ArrayList<Function> mutatePopulation(ArrayList<Function> population) {
        ArrayList<Function> currentPopulation = (ArrayList<Function>) population.clone();
        ArrayList<Function> newGeneration = new ArrayList<Function>();

        int size = population.size();
        double howManyWillBeMutated = Math.ceil((size * 8) / 100);
        double howManyWillBeCreated = Math.ceil((size * 2) / 100);

        System.out.println(String.format("Size: %s", size));
        System.out.println(String.format("Mutated: %s", howManyWillBeMutated));
        System.out.println(String.format("Created: %s", howManyWillBeCreated));

        for(int i = 0; i < size; i ++) {
            int randomIndex = this.random.nextInt(size - i);
            Function func = currentPopulation.get(randomIndex);

            if (howManyWillBeCreated > 0) {
                newGeneration.add(this.getGenerator().generateFunction(this.getDepth()));
                howManyWillBeCreated -= 1;
            } else if (howManyWillBeMutated > 0) {
                MutationOperator operator = this.getRandomMutationOperator();
                operator.mutate(func);
                howManyWillBeMutated -= 1;
            } else {
                CrossOperator operator = this.getRandomCrossOperator();
                Function funcB = operator.getRandomFromList(currentPopulation);
                operator.cross(func, funcB);
            }

            newGeneration.add(func);
            currentPopulation.remove(randomIndex);
        }


        return newGeneration;
    }

    public void run() {
        // 1. Generate initial population
        this.currentPopulation = this.getGenerator().generateInitialPopulation(this.getPopulationSize(), this.getDepth());

        while(true) {
            // 2. Evaluate all population
            for(Node node : this.currentPopulation) {
                this.evaluate(node);
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