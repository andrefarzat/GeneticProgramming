package com.andrefarzat.GP;

import com.andrefarzat.GP.logging.*;
import com.andrefarzat.GP.nodes.*;
import com.andrefarzat.GP.operators.*;

import java.util.List;
import java.util.Random;


public class GP {
    private String name;
    private int index;
    private String[] leftList;
    private String[] rightList;

    public int crossoverCount;
    public int mutationCounter;

    protected int generationNumber = 0;
    protected Population population;
    protected final int maxDepth = 2;
    protected final int populationSize = 1000;
    protected final int crossoverProbability = 80;
    protected final int mutationProbability = 10;
    protected final Random random = new Random();

    public GP(String name, int index, String[] leftList, String[] rightList) {
        this.name = name;
        this.index = index;
        this.leftList = leftList;
        this.rightList = rightList;
        Utils.currentIndividualId = 0;
    }

    public CrossoverOperator crossoverOperator = new SubtreeCrossover();
    public MutationOperator mutationOperator   = new PointMutation();
    public Logger logger = new MongoLogger();

    public String getId() { return this.name + "_" + (this.index < 10 ? "0" : "") + this.index; }
    public Population getPopulation() { return this.population; }
    public int getGenerationNumber() { return generationNumber; }

    public void log(String msg) {
        System.out.println(msg);
    }

    public void log(String msg, Object ...params) {
        this.log(String.format(msg, params));
    }

    protected Individual generateIndividual() {
        Individual individual = new Individual();
        individual.createdInGeneration = this.generationNumber;
        individual.tree = Function.create(this.random.nextInt(this.maxDepth));

        List<Function> funcs = individual.tree.getFunctions();
        Function selectedFunc = Utils.getFromListRandomly(funcs);

        boolean shouldBeLeft = this.random.nextBoolean();
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
        individual.fitness = individual.getValue().length();
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
        Individual neo = this.crossoverOperator.cross(father, mother);
        neo.createdInGeneration = this.generationNumber;
        this.population.add(neo);
        this.logger.logCrossover(this, father, mother, neo);
    }

    public void doMutation(Individual father) {
        Individual neo = this.mutationOperator.mutate(father);
        neo.createdInGeneration = this.generationNumber;
        this.population.add(neo);
        this.logger.logMutation(this, father, neo);
    }

    public boolean shouldStop(Population population) {
        this.log( "Attempt %s", this.generationNumber);
        return (this.generationNumber >= 100);
    }

    public void run() {
        this.logger.logStart(this);

        // 1. Generate initial population
        this.population = this.generateInitialPopulation();

        do {
            this.crossoverCount = 0;
            this.mutationCounter = 0;
            this.population.setGenerationNumber(++this.generationNumber);

            // 2. Evaluate population
            for(Individual individual : this.population.individuals) {
                this.evaluate(individual);
            }

            // 3. Select
            this.doSelection(this.population);
            this.logger.logPopulation(this);

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

        this.logger.logEnd(this);
    }
}
