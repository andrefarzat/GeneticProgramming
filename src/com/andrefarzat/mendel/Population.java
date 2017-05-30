package com.andrefarzat.mendel;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Population {
    protected ArrayList<Individual> individuals = new ArrayList<Individual>();

    public void add(Individual individual) {
        this.individuals.add(individual);
    }

    public void concat(Population population) {
        this.individuals.addAll(population.individuals);
    }

    public void sortByMeasure() {
        Collections.sort(this.individuals);
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

    public Individual getRandomIndividualAndRemoveIt() {
        int size = this.individuals.size();
        if (size == 0) {
            return null;
        }

        int index = Utils.random.nextInt(size);
        return this.getAndRemove(index);
    }
}
