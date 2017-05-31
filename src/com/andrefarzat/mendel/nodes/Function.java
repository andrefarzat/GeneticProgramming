package com.andrefarzat.mendel.nodes;

import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.MendelValue;

import java.util.ArrayList;
import java.util.Collection;


public abstract class Function extends Node {
    public Node left;
    public Node right;

    public abstract MendelValue getValue(MendelValue value);

    public int getDepth() {
        int maxDepth = 0;

        for(Node node : this.getNodes()) {
            int depth = 0;

            if (node instanceof Function) {
                depth = ((Function) node).getDepth() + 1;
            }

            if (depth > maxDepth) {
                maxDepth = depth;
            }
        }


        return maxDepth;
    }

    public Function getFunctionParentOf(Node node) {
        ArrayList<Function> funcs = this.getFunctions();
        funcs.add(0, this);

        for(Function func : funcs) {
            if (func.left == node || func.right == node) return func;
        }

        return null;
    }

    public ArrayList<Node> getNodes() {
        ArrayList<Node> list = new ArrayList<Node>();
        list.add(this.left);
        list.add(this.right);

        if (this.left instanceof Function) {
            list.addAll(((Function) this.left).getNodes());
        }

        if (this.right instanceof Function) {
            list.addAll(((Function) this.right).getNodes());
        }

        return list;
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

    public ArrayList<Terminal> getTerminals() {
        ArrayList<Terminal> terminals = new ArrayList<Terminal>();

        for (Node node : this.getNodes()) {
            if (node instanceof Terminal) {
                terminals.add((Terminal) node);
            } else if (node instanceof Function) {
                Function func = (Function) node;
                terminals.addAll(func.getTerminals());
            }
        }

        return terminals;
    }

    public void replaceNode(Node nodeA, Node nodeB) {
        Function parent = this.getFunctionParentOf(nodeA);

        if (parent == null) {
            // We can't replace =/
        } else {
            if (parent.left  == nodeA) parent.left  = nodeB.clone();
            if (parent.right == nodeA) parent.right = nodeB.clone();
        }
    }
}
