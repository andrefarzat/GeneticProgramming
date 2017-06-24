package com.andrefarzat.GP;


import java.util.ArrayList;

public class Population {
    protected ArrayList<Individual> individuals = new ArrayList<>();

    public void add(Individual individual) {
        this.individuals.add(individual);
    }
}
