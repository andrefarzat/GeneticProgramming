package com.andrefarzat.mendel.operators;


import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Population;
import com.andrefarzat.mendel.Utils;
import com.andrefarzat.mendel.nodes.Function;
import com.andrefarzat.mendel.Mendel;


public class SubtreeMutation extends GeneticOperator {

    public Individual create(Mendel mendel, Individual ind) {
        Function func = Utils.getFromListRandomly(ind.getFunctions());
        Function neoFunc = mendel.getGenerator().generateFunction(func.getDepth());

        boolean shouldBeLeft = Utils.random.nextBoolean();
        if (shouldBeLeft) {
            func.left = neoFunc;
        } else {
            func.right = neoFunc;
        }

        return ind;
    }

    public Individual create(Mendel mendel, Individual ind, Population population) {
        return this.create(mendel, ind);
    }
}
