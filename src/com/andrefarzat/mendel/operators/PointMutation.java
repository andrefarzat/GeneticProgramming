package com.andrefarzat.mendel.operators;

import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Mendel;
import com.andrefarzat.mendel.Population;
import com.andrefarzat.mendel.Utils;
import com.andrefarzat.mendel.nodes.Node;


public class PointMutation implements MutationOperator {

    public Individual mutate(Mendel mendel, Individual ind) {
        ind = ind.clone();
        Node node = Utils.getFromListRandomly(ind.getNodes());
        node.mutate();

        return ind;
    }

}
