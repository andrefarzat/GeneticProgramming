package com.andrefarzat.mendel;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.UUID;

public class Population {
    private UUID id = UUID.randomUUID();
    private int generationNumber;
    public void setGenerationNumber(int generationNumber) { this.generationNumber = generationNumber; }
    public int getGenerationNumber() { return this.generationNumber; }

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

    public void sortByMeasure() {
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

    public Individual getRandomIndividualAndRemoveIt() {
        int size = this.individuals.size();
        if (size == 0) {
            return null;
        }

        int index = Utils.random.nextInt(size);
        return this.getAndRemove(index);
    }

    public Population slice(int from, int to) {
        Population pop = this.clone();
        pop.individuals = new ArrayList(pop.individuals.subList(from, to));
        return pop;
    }
}
