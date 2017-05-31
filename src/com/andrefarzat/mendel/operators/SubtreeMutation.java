package com.andrefarzat.mendel.operators;


import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Population;
import com.andrefarzat.mendel.Utils;
import com.andrefarzat.mendel.nodes.Function;
import com.andrefarzat.mendel.Mendel;


public class SubtreeMutation implements MutationOperator {

    public Individual mutate(Mendel mendel, Individual ind) {
        Individual neo = ind.clone();

        Function func = Utils.getFromListRandomly(neo.getFunctions());

        boolean shouldBeLeft = Utils.random.nextBoolean();
        if (shouldBeLeft) {
            func.left = mendel.getGenerator().generateFunction(mendel.getDepth());
        } else {
            func.right = mendel.getGenerator().generateFunction(mendel.getDepth());
        }

        return neo;
    }

}
