package com.andrefarzat.GP;


import java.util.List;
import java.util.Random;

public class GP {
    protected Population population;
    protected final int maxDepth = 2;
    protected final int populationSize = 1000;
    protected final Random random = new Random();

    public double[][] getParams() {
        return new double[][] { {12d, 13d}, {14d, 15d} }; // Simple Example
        //return new double[][] { {12d, 32d}, {14d, 34d}, {120d, 140d} }; // Ease Example
        //return new double[][] { {100.0d, 200.0d}, {350.0d, 450.0d} }; // Not Ease Example
        //return new double[][] { {1.0d, 33.8d}, {10.0d, 50.0d} }; // celsius to Fahrenheit
        //return new double[][] { {20.0d, 293.15d}, {40.0d, 313.15d} }; // celsius to Kelvin
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

    public void evaluate(Individual individual) {
        double fitness = 0d;

        for(double[] param : this.getParams()) {
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

    public void run() {
        // 1. Generate initial population
        this.population = this.generateInitialPopulation();

        do {
            // 2. Evaluate population
            for(Individual individual : population.individuals) {
                this.evaluate(individual);
            }

            // 3. Select
            //this.doSelection(population);

            // 4. Should stop ?
//            if (this.shouldStop(population)) {
//                break;
//            }

            // 5. Cross
//            this.doCrossover(population);

            // 6. Mutate
//            this.doMutation(population);

            // 7. We loop
        } while (true);
    }
}
