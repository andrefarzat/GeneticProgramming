package com.andrefarzat.mendel;


import com.andrefarzat.mendel.nodes.Function;
import com.andrefarzat.mendel.nodes.Node;
import com.andrefarzat.mendel.nodes.Terminal;

import java.util.ArrayList;
import java.util.Random;

public class Individual implements Comparable<Individual> {
    private float measure;
    protected Function three;

    public void setMeasure(float measure) {
        this.measure = measure;
    }
    public float getMeasure() { return this.measure; }

    public float getValue(float value) {
        float ret = this.three.getValue(value);
        return ret;
    }

    public String toString() { return this.three.toString(); }

    public int compareTo(Individual individual) {
        float m1 = this.getMeasure();
        float m2 = individual.getMeasure();
        float value = this.getMeasure() - individual.getMeasure();

        if (Float.isInfinite(value) || Float.isNaN(value)) {
            System.out.println("Da porra");
        }
        return Float.floatToIntBits(value);
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
