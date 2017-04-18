package com.andrefarzat.GP;

import java.util.Random;
import com.andrefarzat.GP.nodes;


public class GP {
    private Random random = new Random();

    public nodes.Node[] generateInitialPopulation(int n, int x) {
        nodes.Node[] population = new nodes.Node[n];

        for(int i = 0; i < n; i++) {
            population[i] = nodes.generateOperator(x);
        }

        return population;
    }

    public nodes.Node[] mutatePopulation(nodes.Node[] population, int x) {
        for(nodes.Node node : population) {
            node.mutate(x);
        }

        return population;
    }
}
