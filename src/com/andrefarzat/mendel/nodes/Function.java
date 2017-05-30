package com.andrefarzat.mendel.nodes;

import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.MendelValue;

import java.util.ArrayList;


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

    public ArrayList<Node> getNodes() {
        ArrayList<Node> list = new ArrayList<Node>();;
        list.add(this.left);
        list.add(this.right);
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
}
