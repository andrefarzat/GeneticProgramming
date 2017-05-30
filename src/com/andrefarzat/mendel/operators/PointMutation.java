package com.andrefarzat.mendel.operators;

import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Utils;
import com.andrefarzat.mendel.nodes.Node;

import java.util.ArrayList;
import java.util.Random;


public class PointMutation extends MutationOperator {

    public void mutate(Individual ind) {
        Node node = Utils.getFromListRandomly(ind.getNodes());
        node.mutate();
    }
}
