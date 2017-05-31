package com.andrefarzat.GP.operators;

import com.andrefarzat.GP.Value;
import com.andrefarzat.GP.nodes.Literal;
import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Mendel;
import com.andrefarzat.mendel.Utils;
import com.andrefarzat.mendel.nodes.Terminal;
import com.andrefarzat.mendel.operators.MutationOperator;

import java.math.BigDecimal;

public class DecimalMutation implements MutationOperator {

    public Individual mutate(Mendel mendel, Individual ind) {
        Individual neo = ind.clone();
        Terminal terminal = Utils.getFromListRandomly(neo.getTerminals());

        if (terminal instanceof Literal) {
            Value value = (Value) terminal.getValue();
            BigDecimal v = Literal.generateRandomBigDecimalFromRange(new BigDecimal("0.1"), new BigDecimal("0.9"));

            boolean shouldAdd = Utils.random.nextBoolean();
            if (shouldAdd) {
                value.set(value.get().add(v));
            } else {
                value.set(value.get().subtract(v));
            }

            terminal.setValue(value);
        }

        return neo;
    }
}
