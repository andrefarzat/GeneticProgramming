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

    public void addAll(ArrayList<Individual> inds) {
        for(Individual ind : inds) this.add(ind);
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


    public void calculateProbability() {
        double sum = this.getFitnessTotal();

        for (Individual ind : this.individuals) {
            double probability = ind.getFitness() / sum;
            ind.setProbability(probability);
        }
    }
}
