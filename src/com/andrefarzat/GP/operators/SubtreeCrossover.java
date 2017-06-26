package com.andrefarzat.GP.operators;


import com.andrefarzat.GP.Function;
import com.andrefarzat.GP.Individual;
import com.andrefarzat.GP.Node;
import com.andrefarzat.GP.Utils;

import java.util.Random;


public class SubtreeCrossover implements CrossoverOperator {

    public Individual cross(Individual father, Individual mother) {
        Individual neo = father.clone();

        // 1. Getting a random function from father
        Function func = Utils.getFromListRandomly(neo.getTree().getFunctions());

        // 2. Getting a random node from mother
        Node node = Utils.getFromListRandomly(mother.getTree().getNodes()).clone();

        // 3. Replacing the node from mother's node
        boolean shouldBeLeft = (new Random()).nextBoolean();
        if (shouldBeLeft) {
            func.left = node;
        } else {
            func.right = node;
        }

        // 4. Returning from saturn
        return neo;
    }
}
