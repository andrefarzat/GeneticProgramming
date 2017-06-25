package com.andrefarzat.GP;


import java.util.ArrayList;
import java.util.Collections;

public class Population {
    protected ArrayList<Individual> individuals = new ArrayList<>();

    public void add(Individual individual) {
        this.individuals.add(individual);
    }

    public void sortByFitness() {
        try {
            Collections.sort(this.individuals);
        } catch(IllegalArgumentException e) {
            // http://memes.andrefarzat.com/ta-de-boa
            throw e;
        }
    }

    public int size() { return this.individuals.size(); }
}
