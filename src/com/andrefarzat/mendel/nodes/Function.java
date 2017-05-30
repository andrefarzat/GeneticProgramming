package com.andrefarzat.mendel.nodes;

import java.util.ArrayList;


public abstract class Function extends Node {
    public Node left;
    public Node right;

    public float getValue(float value) {
        return this.left.getValue(value) + this.right.getValue(value);
    }

    public ArrayList<Node> getNodes() {
        ArrayList<Node> list = new ArrayList<Node>();
        list.add(this.left);
        list.add(this.right);
        return list;
    }

    public ArrayList<Terminal> getTerminals() {
        ArrayList<Terminal> terminals = new ArrayList<Terminal>();

        for (Node node : this.getNodes()) {
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

        for (Node node : this.getNodes()) {
            if (node instanceof Function) {
                Function func = (Function) node;
                funcs.addAll(func.getFunctions());
            }
        }

        return funcs;
    }
}
