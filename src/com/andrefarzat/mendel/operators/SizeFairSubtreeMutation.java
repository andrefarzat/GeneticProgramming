package com.andrefarzat.mendel.operators;


import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Mendel;
import com.andrefarzat.mendel.Population;
import com.andrefarzat.mendel.Utils;
import com.andrefarzat.mendel.nodes.Function;

public class SizeFairSubtreeMutation implements MutationOperator {

    public Individual mutate(Mendel mendel, Individual indA) {
        Individual neo = indA.clone();
        Function func = Utils.getFromListRandomly(neo.getFunctions());

        boolean shouldBeLeft = Utils.random.nextBoolean();

        if (shouldBeLeft) {
            func.left = mendel.getGenerator().generateFunction(func.getDepth());
        } else {
            func.right = mendel.getGenerator().generateFunction(func.getDepth());
        }

        return neo;
    }
}
