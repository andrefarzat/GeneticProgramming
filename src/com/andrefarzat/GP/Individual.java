package com.andrefarzat.GP;

import java.util.UUID;


public class Individual implements Comparable<Individual> {
    protected UUID id = UUID.randomUUID();
    protected Function tree;
    protected double fitness;

    public double getValue(double value) {
        return this.tree.getValue(value);
    }
    public Function getTree() { return this.tree; }

    public boolean isValid() {
        if (this.tree == null) {
            return false;
        }

        int howManyVariables = 0;

        for(Node node : this.tree.getNodes()) {
            if (node instanceof Variable) {
                howManyVariables += 1;
            }
        }

        return howManyVariables == 1;
    }

    public Individual clone() {
        Individual individual = new Individual();
        individual.id         = UUID.randomUUID();
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
}
