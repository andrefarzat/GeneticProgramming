package com.andrefarzat.GP;

import com.andrefarzat.GP.nodes.Literal;
import com.andrefarzat.GP.nodes.Operator;
import com.andrefarzat.GP.nodes.Variable;
import com.andrefarzat.mendel.IndividualGenerator;
import com.andrefarzat.mendel.nodes.Terminal;
import java.util.Random;


public class Generator extends IndividualGenerator {
    private Random random = new Random();

    public Terminal generateTerminal() {
        if (this.random.nextInt(6) == 1) {
            Variable var = new Variable();
            return var;
        } else {
            Literal literal = new Literal();
            literal.mutate();
            return literal;
        }
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
