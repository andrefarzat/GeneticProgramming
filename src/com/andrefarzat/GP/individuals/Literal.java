package com.andrefarzat.GP.individuals;

import java.util.Random;
import com.andrefarzat.mendel.Terminal;


public class Literal extends Terminal {

    public void mutate() {
        this.value = this.generateRandomValue();
    }

    private float generateRandomValue() {
        float minX = -1.0f;
        float maxX = 1.0f;

        Random random = new Random();
        return random.nextFloat() * (maxX - minX) + minX;
    }
}
