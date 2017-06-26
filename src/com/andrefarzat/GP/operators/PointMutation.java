package com.andrefarzat.GP.operators;


import com.andrefarzat.GP.Individual;
import com.andrefarzat.GP.Node;
import com.andrefarzat.GP.Utils;

public class PointMutation implements MutationOperator {
    public Individual mutate(Individual father) {
        Individual neo = father.clone();
        Node node = Utils.getFromListRandomly(neo.getTree().getNodes());
        node.mutate();

        return neo;
    }
}
