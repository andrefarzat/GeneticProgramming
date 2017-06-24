package com.andrefarzat.GP;


public class Individual {
    protected Function tree;
    protected double fitness;

    public double getValue(double value) {
        return this.tree.getValue(value);
    }

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
}
