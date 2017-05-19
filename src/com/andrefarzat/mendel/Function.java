package com.andrefarzat.mendel;

import java.util.ArrayList;


public abstract class Function extends Individual {
    protected Individual left;
    protected Individual right;

    public float getValue(float value) {
        return this.left.getValue(value) + this.right.getValue(value);
    }

    public Individual[] getNodes() {
        return new Individual[] { this.left, this.right };
    }

    public ArrayList<Terminal> getTerminals() {
        ArrayList<Terminal> terminals = new ArrayList<Terminal>();

        for (Individual node : this.getNodes()) {
            if (node instanceof Terminal) {
                terminals.add((Terminal) node);
            }

            if (node instanceof Function) {
                Function func = (Function) node;
                terminals.addAll(func.getTerminals());
            }
        }

        return terminals;
    }

    public ArrayList<Function> getFunctions() {
        ArrayList<Function> funcs = new ArrayList<Function>();

        for (Individual node : this.getNodes()) {
            if (node instanceof Function) {
                Function func = (Function) node;
                funcs.addAll(func.getFunctions());
            }
        }

        return funcs;
    }
}
