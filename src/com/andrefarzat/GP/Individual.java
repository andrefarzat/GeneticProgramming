package com.andrefarzat.GP;


public class Individual {
    protected Node tree;
    protected double fitness;

    public Individual() {
        this.tree = new Function();
    }

    public boolean isValid() {
        return false;
    }
}
