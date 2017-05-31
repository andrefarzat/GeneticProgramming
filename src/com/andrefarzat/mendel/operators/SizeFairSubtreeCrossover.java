package com.andrefarzat.mendel.operators;

import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Mendel;
import com.andrefarzat.mendel.Utils;
import com.andrefarzat.mendel.nodes.Function;

import java.util.ArrayList;


public class SizeFairSubtreeCrossover implements CrossoverOperator {

    public ArrayList<Individual> cross(Mendel mendel, Individual indA, Individual indB) {
        Individual neoA = indA.clone();
        Individual neoB = indB.clone();

        // 1. Getting any func from B
        Function funcA = null;
        Function funcB = Utils.getFromListRandomly(neoB.getFunctions());

        // 2. Getting any func from A which has the same size of B
        for(Function func : neoA.getFunctions()) {
            if (funcB.getDepth() == func.getDepth()) {
                funcA = func;
            }
        }

        if (funcA == null) {
            // Alternative path: get any func from A
            funcA = Utils.getFromListRandomly(neoA.getFunctions());
        }

        // 4. We cross !
        neoA.getTree().replaceNode(funcA, funcB);
        neoB.getTree().replaceNode(funcB, funcA);

        // 5. Return to real life
        ArrayList<Individual> ret = new ArrayList<Individual>();
        ret.add(neoA);
        ret.add(neoB);
        return ret;
    }
}
