package com.andrefarzat.mendel.operators;


import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Mendel;
import com.andrefarzat.mendel.Population;
import com.andrefarzat.mendel.Utils;
import com.andrefarzat.mendel.nodes.Function;
import com.andrefarzat.mendel.nodes.Node;


public class SubtreeCrossover extends GeneticOperator {

    public Individual create(Mendel mendel, Individual ind, Population population) {
        Individual indA = ind.clone();
        Individual indB = population.get(0); // The best at the moment

        if (indB == null) {
            // Population is empty? Well, let's create one and cross with it
            indB = mendel.getGenerator().generateIndividual(mendel.getDepth());
        }

        // 1. Getting a random function from indA
        Function func = Utils.getFromListRandomly(indA.getFunctions());

        // 2. Getting a random node from indB
        Node node = Utils.getFromListRandomly(indB.getNodes()).clone();

        // 3. Replacing either left or right from the gotten function
        boolean shouldBeLeft = Utils.random.nextBoolean();
        if (shouldBeLeft) {
            func.left = node;
        } else {
            func.right = node;
        }

        return indA;
    }
}
