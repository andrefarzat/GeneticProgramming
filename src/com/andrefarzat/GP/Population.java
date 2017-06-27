package com.andrefarzat.GP;


import java.util.ArrayList;
import java.util.Collections;

public class Population {
    protected int generationNumber = 0;
    protected ArrayList<Individual> individuals = new ArrayList<>();

    public void setGenerationNumber(int generationNumber) { this.generationNumber = generationNumber; }
    public void add(Individual individual) {
        this.individuals.add(individual);
    }
    public int size() { return this.individuals.size(); }
    public Individual get(int index) { return this.individuals.get(index); }

    public Individual getAtRandom() {
        return this.getAtRandom(this.individuals.size());
    }

    public Individual getAtRandom(int size) {
        int index = Utils.random.nextInt(size);
        return this.get(index);
    }

    public void sortByFitness() {
        try {
            Collections.sort(this.individuals);
        } catch(IllegalArgumentException e) {
            // http://memes.andrefarzat.com/ta-de-boa
            throw e;
        }
    }
}
