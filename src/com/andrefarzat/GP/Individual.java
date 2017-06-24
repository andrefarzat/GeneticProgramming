package com.andrefarzat.GP;


public class Individual {
    protected Node tree;
    protected double fitness;

    public Individual() {
        this.tree = new Function();
    }

    public double getValue(double value) {
        return this.tree.getValue(value);
    }

    public boolean isValid() {
        return false;
    }
}
