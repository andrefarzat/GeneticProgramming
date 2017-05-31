package com.andrefarzat.mendel;


import com.andrefarzat.mendel.nodes.Function;
import com.andrefarzat.mendel.nodes.Node;
import com.andrefarzat.mendel.nodes.Terminal;

import java.util.UUID;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Individual implements Comparable<Individual> {
    private BigDecimal measure;
    private Function tree;
    private UUID uid;

    public Individual() {
        this.uid = UUID.randomUUID();
    }

    public Individual(UUID uid) {
        this.uid = uid;
    }

    public UUID getUid() {
        return this.uid;
    }

    public void setMeasure(BigDecimal measure) {
        measure.setScale(1, BigDecimal.ROUND_DOWN);
        this.measure = measure;
    }
    public BigDecimal getMeasure() { return this.measure; }

    public void setTree(Function tree) { this.tree = tree; }
    public Function getTree() { return this.tree; }

    public MendelValue getValue(MendelValue value) {
        return this.tree.getValue(value);
    }

    public String toString() { return this.tree.toString(); }

    public Individual clone() {
        Individual ind = new Individual(this.uid);
        ind.tree = (Function) this.tree.clone();
        ind.measure = this.measure;
        return ind;
    }

    public int compareTo(Individual individual) {
        int result = this.getMeasure().compareTo(individual.getMeasure());
        if (result == 0) {
            // They are equal? The untie is the depth
            int thisSize = this.getFunctions().size();
            int indSize  = individual.getFunctions().size();

            if (thisSize > indSize) return 1;
            if (thisSize < indSize) return -1;
            return 0; // Well, they are really equal
        }

        return result;
    }

    public ArrayList<Node> getNodes() {
        ArrayList<Node> list = new ArrayList<Node>();
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
        ArrayList<Terminal> terminals = new ArrayList<Terminal>();

        for (Node node : this.getNodes()) {
            if (node instanceof Function) {
                Function func = (Function) node;
                terminals.addAll(func.getTerminals());
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
