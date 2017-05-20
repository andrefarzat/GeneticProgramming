package com.andrefarzat.GP.individuals;

import com.andrefarzat.mendel.individuals.Function;
import com.andrefarzat.mendel.individuals.IndividualGenerator;
import com.andrefarzat.mendel.individuals.Terminal;

import java.util.Random;


public class Generator extends IndividualGenerator {
    private Random random = new Random();

    public Terminal generateTerminal() {
        Terminal terminal = this.random.nextInt(6) == 1 ? new Variable() : new Literal();
        terminal.mutate();
        return terminal;
    }

    public Operator generateFunction(int depth) {
        Operator operator = new Operator();

        if (depth <= 0) {
            operator.left  = this.generateTerminal();
            operator.right = this.generateTerminal();
        } else {
            operator.left  = this.generateFunction(depth - 1);
            operator.right = this.generateFunction(depth - 1);
        }

        return operator;
    }
}
