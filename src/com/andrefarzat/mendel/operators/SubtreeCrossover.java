package com.andrefarzat.mendel.operators;


import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Utils;
import com.andrefarzat.mendel.nodes.Function;
import com.andrefarzat.mendel.nodes.Node;

import java.util.ArrayList;
import java.util.Random;


public class SubtreeCrossover extends CrossOperator {

    public Individual cross(Individual indA, Individual indB) {
        Individual ind = indA.clone();

        // 1. Getting a random function from indA
        Function func = Utils.getFromListRandomly(ind.getFunctions());

        // 2. Getting a random node from indB\
        Node node = Utils.getFromListRandomly(indB.getNodes()).clone();


        // 3. Replacing either left or right from the gotten function
        boolean shouldBeLeft = (new Random()).nextInt(2) == 1;
        if (shouldBeLeft) {
            func.left = node;
        } else {
            func.right = node;
        }

        return ind;
    }
}
