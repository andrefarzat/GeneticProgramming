package com.andrefarzat.mendel.operators;


import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Utils;
import com.andrefarzat.mendel.nodes.Function;
import com.andrefarzat.mendel.nodes.Node;

import java.util.ArrayList;
import java.util.Random;


public class SubtreeCrossover extends CrossOperator {

    public Individual cross(Individual indA, Individual indB) {
        Individual ind = null;
        ArrayList<Node> nodes = indA.getNodes();
        ArrayList<Function> funcs = indA.getFunctions();

        Function func = (Function) Utils.getFromListRandomly(funcs).clone();
        Node node = (Node) Utils.getFromListRandomly(nodes).clone();


        boolean shouldBeLeft = (new Random()).nextInt(2) == 1;
        if (shouldBeLeft) {
            func.left = node;
        } else {
            func.right = node;
        }

        return ind;
    }
}
