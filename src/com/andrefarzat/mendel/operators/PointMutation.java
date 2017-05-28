package com.andrefarzat.mendel.operators;

import com.andrefarzat.mendel.nodes.Node;


public class PointMutation extends MutationOperator {

    public void mutate(Node node) {
        node.mutate();
    }

}
