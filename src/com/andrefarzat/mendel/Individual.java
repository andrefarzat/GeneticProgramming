package com.andrefarzat.mendel;


import com.andrefarzat.mendel.nodes.Function;
import com.andrefarzat.mendel.nodes.Node;
import com.andrefarzat.mendel.nodes.Terminal;

import java.util.ArrayList;

public class Individual implements Comparable<Individual> {
    private double measure;
    protected Function three;

    public void setMeasure(double measure) { this.measure = measure; }
    public double getMeasure() { return this.measure; }

    public MendelValue getValue(MendelValue value) {
        return this.three.getValue(value);
    }

    public String toString() { return this.three.toString(); }

    public Individual clone() {
        Individual ind = new Individual();
        ind.three = (Function) this.three.clone();
        ind.measure = this.measure;
        return ind;
    }

    public int compareTo(Individual individual) {
        return (int) Double.doubleToLongBits(this.getMeasure() - individual.getMeasure());
    }

    public ArrayList<Node> getNodes() {
        ArrayList<Node> list = new ArrayList<Node>();
        list.add(this.three);
        list.add(this.three.left);
        list.add(this.three.right);
        return list;
    }

    public ArrayList<Terminal> getTerminals() {
        ArrayList<Terminal> terminals = new ArrayList<Terminal>();

        for (Node node : this.getNodes()) {
            if (node instanceof Terminal) {
                Terminal terminal = (Terminal) node;
                terminals.add(terminal);
            }
        }

        return terminals;
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
