package com.andrefarzat.mendel.operators;

import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Mendel;
import com.andrefarzat.mendel.Utils;
import com.andrefarzat.mendel.nodes.Node;


public class PointMutation extends GeneticOperator {

    public Individual create(Mendel mendel, Individual ind) {
        Node node = Utils.getFromListRandomly(ind.getNodes());
        node.mutate();

        return ind;
    }

}
