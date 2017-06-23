package com.andrefarzat.mendel.selectors;

import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Population;

import java.util.Random;


public class AleatorySelector implements Selector {
    private Random random = new Random();

    public Selection get(int i, int size, Population population) {
        if (i < (size * 0.8)) {
            Selection selection = new Selection(Selection.Types.crossover, this.getOneAtRandom(size, population), this.getOneAtRandom(size, population));
            return selection;
        }

        if (i < (size * 0.9)) {
            Selection selection = new Selection(Selection.Types.mutation, this.getOneAtRandom(size, population));
            return selection;
        }

        // Default? We discard
        Selection selection = new Selection(Selection.Types.discarded);
        return selection;
    }

    private Individual getOneAtRandom(int size, Population population) {
        int index = this.random.nextInt(size);
        return population.get(index);
    }
}
