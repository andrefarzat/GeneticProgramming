package com.andrefarzat.mendel;


import com.andrefarzat.mendel.nodes.Function;
import com.andrefarzat.mendel.nodes.Node;
import com.andrefarzat.mendel.nodes.Terminal;

import java.util.ArrayList;
import java.util.UUID;


public class Individual implements Comparable<Individual> {
    private UUID id = UUID.randomUUID();
    public String getId() { return this.id.toString(); }

    private double measure;
    public void setMeasure(double measure) { this.measure = measure; }
    public double getMeasure() { return this.measure; }

    private Function tree;
    public void setTree(Function tree) { this.tree = tree; }
    public Function getTree() { return this.tree; }

    public String toString() { return this.tree.toString(); }

    public Individual clone() {
        Individual ind = new Individual();
        ind.tree = this.tree.clone();
        ind.measure = this.measure;
        return ind;
    }

    public double getValue(double value) {
        return this.getTree().getValue(value);
    }

    public int compareTo(Individual individual) {
        int result = Utils.compareDouble(this.getMeasure(), individual.getMeasure());

        if (result == 0) {
            // They are equal? The tiebreaker is the depth
            int thisSize = this.getFunctions().size();
            int indSize  = individual.getFunctions().size();

            if (thisSize > indSize) return 1;
            if (thisSize < indSize) return -1;
            return 0; // Well, they are really equal
        }

        return result;
    }

    public ArrayList<Node> getNodes() {
        ArrayList<Node> list = new ArrayList();
        list.add(this.tree);
        list.add(this.tree.left);
        list.add(this.tree.right);

        if (this.tree.left instanceof Function) {
            list.addAll(((Function) this.tree.left).getNodes());
        }

        if (this.tree.right instanceof Function) {
            list.addAll(((Function) this.tree.right).getNodes());
        }
        return list;
    }

    public ArrayList<Terminal> getTerminals() {
        ArrayList<Terminal> terminals = new ArrayList();

        for (Node node : this.getNodes()) {
            if (node instanceof Function) {
                Function func = (Function) node;
                terminals.addAll(func.getTerminals());
            }
        }

        return terminals;
    }

    public ArrayList<Function> getFunctions() {
        ArrayList<Function> funcs = new ArrayList();

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
