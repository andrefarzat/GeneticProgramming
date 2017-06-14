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
        int first = Utils.random.nextInt(10);
        int second = Utils.random.nextInt(10) + 1;

        return Double.parseDouble(first + "." + second);
    }
}
