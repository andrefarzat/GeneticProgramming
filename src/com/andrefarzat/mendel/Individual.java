package com.andrefarzat.mendel;


import com.andrefarzat.mendel.nodes.Function;
import com.andrefarzat.mendel.nodes.Node;
import com.andrefarzat.mendel.nodes.Terminal;

import java.util.ArrayList;
import java.util.Random;

public class Individual implements Comparable<Individual> {
    private float measure;
    protected Function three;

    public void setMeasure(float measure) { this.measure = measure; }
    public float getMeasure() { return this.measure; }

    public float getValue(float value) {
        return this.three.getValue(value);
    }

    public String toString() { return this.three.toString(); }

    public int compareTo(Individual individual) {
        return Float.floatToIntBits(this.getMeasure() - individual.getMeasure());
    }

    public ArrayList<Node> getNodes() {
        ArrayList<Node> list = new ArrayList<Node>();
        list.add(this.three);
        list.add(this.three.left);
        list.add(this.three.right);
        return list;
    }

    public ArrayList<Function> getFunctions() {
        ArrayList<Function> funcs = new ArrayList<Function>();

        for (Node node : this.getNodes()) {
            if (node instanceof Function) {
                Function func = (Function) node;
                funcs.add(func);
                funcs.addAll(func.getFunctions());
            }
        }

        return funcs;
    }
}
