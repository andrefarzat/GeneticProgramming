package com.andrefarzat.GP;

import java.util.Random;
import com.andrefarzat.GP.nodes;


public class GP {
    private Random random = new Random();

    public nodes.Node[] generateInitialPopulation(int n) {
        nodes.Node[] population = new nodes.Node[n];

        for(int i = 0; i < n; i++) {
            population[i] = nodes.generateOperator();
        }

        return population;
    }

    public nodes.Node[] mutatePopulation(nodes.Node[] population) {
        for(nodes.Node node : population) {
            node.mutate();
        }

        return population;
    }

    public boolean givenNodeIsValidSolution(nodes.Node node, int[][] givenParams) {
        boolean hasError = false;

        for(int[] params : givenParams) {
            int x = params[0];
            int y = params[1];
            int value = node.getValue(x);

            hasError = value != y;
        }

        return !hasError;
    }
}
