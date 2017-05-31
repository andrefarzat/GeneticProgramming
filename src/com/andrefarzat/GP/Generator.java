package com.andrefarzat.GP;

import com.andrefarzat.GP.nodes.Literal;
import com.andrefarzat.GP.nodes.Operator;
import com.andrefarzat.GP.nodes.Variable;
import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.IndividualGenerator;
import com.andrefarzat.mendel.Utils;
import com.andrefarzat.mendel.nodes.Terminal;

import java.util.ArrayList;
import java.util.Random;


public class Generator extends IndividualGenerator {
    private Random random = new Random();

    public Terminal generateTerminal() {
        if (this.random.nextInt(10) == 1) {
            return new Variable();
        } else {
            Literal literal = new Literal();
            literal.mutate();
            return literal;
        }
    }

    public Operator generateFunction(int depth) {
        Operator operator = new Operator();
        operator.mutate();

        if (depth <= 0) {
            boolean shouldBeLeft = Utils.random.nextBoolean();
            operator.left  = shouldBeLeft  ? new Variable() : this.generateTerminal();
            operator.right = !shouldBeLeft ? new Variable() : this.generateTerminal();
        } else {
            operator.left  = this.generateFunction(depth - 1);
            operator.right = this.generateFunction(depth - 1);
        }

        return operator;
    }

    public boolean validateIndividual(Individual ind) {
        boolean hasOneVariable = false;
        boolean hasOneLiteral  = false;

        ArrayList<Terminal> terminals = ind.getTerminals();
        for(Terminal terminal : terminals) {
            if (terminal instanceof Literal) {
                hasOneLiteral = true;
            } else if (terminal instanceof Variable) {
                hasOneVariable = true;
            }
        }

        // A valid individual Must have at least one Variable and Can't be composed only by variables in its terminals
        return hasOneLiteral && hasOneVariable;
    }
}
