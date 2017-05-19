package com.andrefarzat.mendel.mutation_operators;

import java.util.ArrayList;
import java.util.Random;

import com.andrefarzat.mendel.Function;


public abstract class MutationOperator {

    public abstract void mutate(Function func);

    protected <T> T getRandomFromList(ArrayList<T> list) {
        int index = (new Random()).nextInt(list.size()) - 1;
        if (index < 0) index = 0;
        return list.get(index);
    }

    protected Function getRandomFunction(Function func) {
        ArrayList<Function> funcs = func.getFunctions();
        funcs.add(func);
        return this.getRandomFromList(funcs);
    }

}
