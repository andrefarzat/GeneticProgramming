package com.andrefarzat.mendel.operators;


import com.andrefarzat.mendel.nodes.Function;
import com.andrefarzat.mendel.nodes.Node;

import java.util.ArrayList;
import java.util.Random;


public class SubtreeCrossover extends CrossOperator {

    public Function cross(Function funcA, Function funcB) {
        Function func = null;
        ArrayList<Function> funcs = funcA.getFunctions();

        func = funcs.size() == 0 ? funcA : (Function) this.getRandomFromList(funcA.getFunctions()).clone();

        boolean shouldBeLeft = (new Random()).nextInt(2) == 2;
        if (shouldBeLeft) {
            func.left = this.getRandomFromList(funcB.getNodes()).clone();
        } else {
            func.right = this.getRandomFromList(funcB.getNodes()).clone();
        }

        return func;
    }
}
