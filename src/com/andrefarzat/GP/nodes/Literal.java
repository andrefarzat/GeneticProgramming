package com.andrefarzat.GP.nodes;

import java.util.Random;
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

    private float generateRandomValue() {
        float minX = -1.0f;
        float maxX = 1.0f;

        Random random = new Random();
        float value = 0;
        while (value == 0) {
            // To ensure value is NEVER zero
            value = random.nextFloat() * (maxX - minX) + minX;
        }

        return Float.parseFloat(String.format("%.2f", value));
    }
}
