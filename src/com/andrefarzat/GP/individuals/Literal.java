package com.andrefarzat.GP.individuals;

import java.util.Random;
import com.andrefarzat.mendel.Individual;


public class Literal extends Individual {

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
