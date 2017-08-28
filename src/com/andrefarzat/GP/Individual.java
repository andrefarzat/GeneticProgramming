package com.andrefarzat.GP;

import com.andrefarzat.GP.nodes.Function;

import java.util.UUID;


public class Individual implements Comparable<Individual> {
    protected UUID id = UUID.randomUUID();
    protected int indId = Utils.nextIndividualId();
    protected Function tree;
    protected double fitness;

    public int createdInGeneration;

    public String getId() { return this.id.toString(); }
    public int getIndId() { return this.indId; }
    public String getValue() { return this.tree.getValue(); }
    public Function getTree() { return this.tree; }
    public double getFitness() { return Utils.fixDouble(this.fitness); }

    public boolean isValid(String[] leftList, String[] rightList) {
        String regex = this.getValue();

        for(String item : leftList) {
            if (!item.matches(regex)) {
                return false;
            }
        }

        for(String item : rightList) {
            if (item.matches(regex)) {
                return false;
            }
        }

        return true;
    }

    public Individual clone() {
        Individual individual = new Individual();
        individual.id         = UUID.randomUUID();
        individual.indId      = Utils.nextIndividualId();
        individual.tree       = this.tree.clone();
        individual.fitness    = this.fitness;

        return individual;
    }

    @Override
    public int compareTo(Individual individual) {
        int result = Utils.compareDouble(this.fitness, individual.fitness);

        if (result == 0) {
            // Are they equal? The tiebreaker is the depth
            int thisSize = this.tree.getFunctions().size();
            int indSize  = individual.tree.getFunctions().size();

            if (thisSize > indSize) return 1;
            if (thisSize < indSize) return -1;
            return 0; // Well, they are really equal
        }

        return result;
    }

    public String toString() {
        return this.tree.toString();
    }
}
