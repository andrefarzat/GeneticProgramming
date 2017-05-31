package com.andrefarzat.GP.nodes;

import java.math.BigDecimal;
import java.util.Random;

import com.andrefarzat.GP.Value;
import com.andrefarzat.mendel.Utils;
import com.andrefarzat.mendel.nodes.Terminal;


public class Literal extends Terminal {

    public void mutate() {
        this.setValue(this.generateRandomValue());
    }

    public Literal clone() {
        Literal literal = new Literal();
        literal.setValue(this.getValue());
        return literal;
    }

    public Value generateRandomValue() {
        BigDecimal min = new BigDecimal("1");
        BigDecimal max = new BigDecimal("50");

        BigDecimal value = this.generateRandomBigDecimalFromRange(min, max);

        Value v = new Value();
        v.set(value);
        return v;
    }

    public static BigDecimal generateRandomBigDecimalFromRange(BigDecimal min, BigDecimal max) {
        BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(1, BigDecimal.ROUND_DOWN);
    }
}
