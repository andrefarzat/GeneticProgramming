package com.andrefarzat.mendel;


import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class Population {
    private int generationNumber;
    public void setGenerationNumber(int generationNumber) { this.generationNumber = generationNumber; }
    public int getGenerationNumber() { return this.generationNumber; }

    private double fitnessTotal = 0;
    public double getFitnessTotal() {
        if (Utils.compareDouble(this.fitnessTotal, 0) == 0) {
            for(Individual ind : this.individuals) {
                this.fitnessTotal += ind.getFitness();
            }
        }

        return this.fitnessTotal;
    }

    private UUID id = UUID.randomUUID();
    public String getId() {
        return this.id.toString();
    }

    protected ArrayList<Individual> individuals = new ArrayList<Individual>();

    public void add(Individual individual) {
        this.individuals.add(individual);
    }

    public void add(int index, Individual individual) { this.individuals.add(index, individual); }

    public void addAll(ArrayList<Individual> inds) {
        for(Individual ind : inds) this.add(ind);
    }

    public void concat(Population population) {
        this.individuals.addAll(population.individuals);
    }

    public void sortByFitness() {
        try {
            Collections.sort(this.individuals);
        } catch(IllegalArgumentException e) {
            // http://memes.andrefarzat.com/ta-de-boa
            throw e;
        }
    }

    public Individual get(int i) {
        return this.individuals.get(i);
    }

    public ArrayList<Individual> getAll() {
        return this.individuals;
    }

    public Population clone() {
        Population pop = new Population();
        pop.individuals = (ArrayList<Individual>) this.individuals.clone();
        return pop;
    }

    public int size() {
        return this.individuals.size();
    }

    public Individual getAndRemove(int index) {
        Individual individual = this.individuals.get(index);
        this.individuals.remove(index);
        return individual;
    }

    public Individual getRandomIndividual() {
        return Utils.getFromListRandomly(this.individuals);
    }

    public Individual[] selectTwoIndividuals() {
        Individual[] neos = new Individual[2];

        int i = 0;
        int numberA = Utils.random.nextInt(10);
        int numberB = Utils.random.nextInt(10);
        double chance = Double.parseDouble(String.format("0.%s%s", numberA, numberB));

        for (Individual ind : this.individuals) {
            double probability = ind.getProbability(); // This is because the lower fitness the better
            System.out.println(String.format("[%s > %s]", chance, probability));
            if (Utils.compareDouble(chance, probability) == 1) {
                neos[i] = ind;
                i++;

                if (i >= 2) break;
            }
        }

        return neos;
    }

    public void calculateProbability() {
        double sum = this.getFitnessTotal();

        for (Individual ind : this.individuals) {
            double probability = ind.getFitness() / sum;
            ind.setProbability(probability);
        }
    }
}
