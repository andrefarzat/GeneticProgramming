package com.andrefarzat.mendel.mutation_operators;

import com.andrefarzat.mendel.Function;
import com.andrefarzat.mendel.Terminal;


public class PointMutation extends MutationOperator {

    public void mutate(Function func) {
        func = this.getRandomFunction(func);
        Terminal terminal = this.getRandomFromList(func.getTerminals());
        terminal.mutate();
    }

}
