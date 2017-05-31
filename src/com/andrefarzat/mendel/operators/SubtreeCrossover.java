package com.andrefarzat.mendel.operators;


import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Mendel;
import com.andrefarzat.mendel.Population;
import com.andrefarzat.mendel.Utils;
import com.andrefarzat.mendel.nodes.Function;
import com.andrefarzat.mendel.nodes.Node;

import java.util.ArrayList;


public class SubtreeCrossover implements CrossoverOperator {

    public ArrayList<Individual> cross(Mendel mendel, Individual indA, Individual indB) {
        Individual neoA = indA.clone();
        Individual neoB = indB.clone();

        // 1. Getting a random node from A
        Node nodeA = Utils.getFromListRandomly(neoA.getNodes());

        // 2. Getting a random node from indB
        Node nodeB = Utils.getFromListRandomly(neoB.getNodes());

        // 3. Switching them
        neoA.getTree().replaceNode(nodeA, nodeB);
        neoB.getTree().replaceNode(nodeB, nodeA);

        // 4. Returning from saturn
        ArrayList<Individual> ret = new ArrayList<Individual>();
        ret.add(neoA);
        ret.add(neoB);
        return ret;
    }
}
