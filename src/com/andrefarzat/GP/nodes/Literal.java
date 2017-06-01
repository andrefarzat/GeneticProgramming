package com.andrefarzat.GP.nodes;

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

    public double generateRandomValue() {
        double min = 0.0f;
        double max = 50.0f;

        double value = 0d;
        while (Double.compare(value, 0d) == 0) {
            // To ensure value is NEVER zero
            value = Utils.random.nextDouble() * (max - min) + min;
        }

        return value;
    }
}
