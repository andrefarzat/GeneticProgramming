package com.andrefarzat.GP;


import com.andrefarzat.GP.operators.*;

import java.util.List;
import java.util.Random;

public class GP {
    protected int generationNumber = 0;
    protected Population population;
    protected final int maxDepth = 2;
    protected final int populationSize = 1000;
    protected final int crossoverProbability = 80;
    protected final int mutationProbability = 10;
    protected final Random random = new Random();

    public double[][] getParams() {
        return new double[][] { {12d, 13d}, {14d, 15d} }; // Simple Example
        //return new double[][] { {12d, 32d}, {14d, 34d}, {120d, 140d} }; // Ease Example
        //return new double[][] { {100.0d, 200.0d}, {350.0d, 450.0d} }; // Not Ease Example
        //return new double[][] { {1.0d, 33.8d}, {10.0d, 50.0d} }; // celsius to Fahrenheit
        //return new double[][] { {20.0d, 293.15d}, {40.0d, 313.15d} }; // celsius to Kelvin
    }

    protected void log(String msg) {
        System.out.println(msg);
    }

    protected void log(String msg, Object ...params) {
        this.log(String.format(msg, params));
    }

    protected Individual generateIndividual() {
        Individual individual = new Individual();
        individual.tree = Function.create(this.random.nextInt(this.maxDepth));

        List<Function> funcs = individual.tree.getFunctions();
        Function selectedFunc = Utils.getFromListRandomly(funcs);

        boolean shouldBeLeft = this.random.nextBoolean();
        if (shouldBeLeft) {
            selectedFunc.left = Variable.create();
        } else {
            selectedFunc.right = Variable.create();
        }

        return individual;
    }

    public Population generateInitialPopulation() {
        Population population = new Population();

        for(int i = 0; i < this.populationSize; i++) {
            population.add(this.generateIndividual());
        }

        return population;
    }

    public void evaluate(Individual individual, double[][] params) {
        double fitness = 0d;

        for(double[] param : params) {
            double value = individual.getValue(param[0]);

            if (Utils.compareDouble(value, 0d) == -1) {
                // Negative? We punish it with a high measure
                fitness = 1000d;
            } else {
                double result = value - param[1];
                fitness += Math.abs(result);
            }
        }

        individual.fitness = fitness;
    }

    public void doSelection(Population population) {
        // selecting by elitism
        population.sortByFitness();

        int size = population.size();

        while (size > this.populationSize) {
            population.individuals.remove(size - 1);
            size -= 1;
        }
    }

    public void doCrossover(Individual father) {
        Individual mother = this.population.getAtRandom(this.populationSize);
        CrossoverOperator operator = new SubtreeCrossover();
        Individual neo = operator.cross(father, mother);
        this.population.add(neo);
    }

    public void doMutation(Individual father) {
        MutationOperator operator = new PointMutation();
        Individual neo = operator.mutate(father);
        this.population.add(neo);
    }

    public boolean shouldStop(Population population) {
        this.log( "Attempt %s", this.generationNumber);

        boolean isFirst = true;

        for(Individual individual : population.individuals) {

            boolean isValid = true;
            for(double[] param : this.getParams()) {
                double value = individual.getValue(param[0]);

                if (isFirst) {
                    String txt = individual.toString();
                    this.log("[Measure: %.2f]F(%s): %s = %.2f", individual.fitness, param[0], txt, value);
                }

                if (Utils.compareDouble(value, param[1]) != 0) {
                    isValid = false;
                }
            }

            if(isValid) {
                for(double[] param : this.getParams()) {
                    this.log("F(%s): %s = %.2f", param[0], individual.toString(), param[1]);
                }
                this.log("Solution found in %s generations of %s individuals! o/", this.generationNumber, this.populationSize);
                return true;
            }

            isFirst = false;
        }

        return false;
    }

    public void run() {
        // 1. Generate initial population
        this.population = this.generateInitialPopulation();

        do {
            // 2. Evaluate population
            for(Individual individual : this.population.individuals) {
                this.evaluate(individual, this.getParams());
            }

            // 3. Select
            this.doSelection(this.population);

            // 4. Should stop ?
            if (this.shouldStop(this.population)) {
                break;
            }

            // 5. Cross and Mutation
            for (int i = 0; i < this.populationSize; i ++) {
                Individual individual = this.population.get(i);

                int chance = this.random.nextInt(100);
                if (chance < this.crossoverProbability) {
                    this.doCrossover(individual);
                }

                chance = this.random.nextInt(100);
                if (chance < this.mutationProbability) {
                    this.doMutation(individual);
                }
            }

            // 6. We loop
        } while (true);
    }
}
