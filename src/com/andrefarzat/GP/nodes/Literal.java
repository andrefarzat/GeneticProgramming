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

    private Value generateRandomValue() {
        BigDecimal min = new BigDecimal("0.1").setScale(1, BigDecimal.ROUND_DOWN);
        BigDecimal max = new BigDecimal("50").setScale(1, BigDecimal.ROUND_DOWN);

        BigDecimal value = this.generateRandomBigDecimalFromRange(min, max);

        Value v = new Value();
        v.set(value);
        return v;
    }

    private BigDecimal generateRandomBigDecimalFromRange(BigDecimal min, BigDecimal max) {
        BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(2, BigDecimal.ROUND_DOWN);
    }
}
