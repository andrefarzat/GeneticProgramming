package com.andrefarzat.mendel.operators;


import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Mendel;
import com.andrefarzat.mendel.Utils;
import com.andrefarzat.mendel.nodes.Function;
import com.andrefarzat.mendel.nodes.Node;

public class SizeFairSubtreeMutation extends GeneticOperator {

    public Individual create(Mendel mendel, Individual ind) {
        Individual neoInd = ind.clone();
        Function func = Utils.getFromListRandomly(neoInd.getFunctions());

        boolean shouldBeLeft = Utils.random.nextBoolean();

        if (shouldBeLeft) {
            func.left = mendel.getGenerator().generateFunction(func.getDepth());
        } else {
            func.right = mendel.getGenerator().generateFunction(func.getDepth());
        }

        return neoInd;
    }
}
