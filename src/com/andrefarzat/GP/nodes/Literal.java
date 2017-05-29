package com.andrefarzat.GP.nodes;

import java.util.Random;
import com.andrefarzat.mendel.nodes.Terminal;


public class Literal extends Terminal {

    public void mutate() {
        this.value = this.generateRandomValue();
    }

    private float generateRandomValue() {
        float minX = -10.0f;
        float maxX = 10.0f;

        Random random = new Random();
        float value = random.nextFloat() * (maxX - minX) + minX;
        return Float.parseFloat(String.format("%.2f", value));
    }
}
