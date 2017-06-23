package com.andrefarzat.mendel.selectors;


import com.andrefarzat.mendel.Population;

public class ElitismSelector implements Selector {

    @Override
    public Selection get(int i, int size, Population population) {
        if (i == 0) {
            // The first goes on
            Selection selection = new Selection(Selection.Types.clone, population.get(i));
            return selection;
        }

        if (i < (size * 0.8)) {
            Selection selection = new Selection(Selection.Types.crossover, population.get(i), population.get(i + 1));
            return selection;
        }

        if (i < (size * 0.9)) {
            Selection selection = new Selection(Selection.Types.mutation, population.get(i));
            return selection;
        }

        // Default? We discard
        Selection selection = new Selection(Selection.Types.discarded);
        return selection;
    }

}
