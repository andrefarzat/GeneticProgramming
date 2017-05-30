package com.andrefarzat.mendel;

import java.util.ArrayList;
import java.util.Arrays;

import com.andrefarzat.mendel.nodes.Node;
import com.andrefarzat.mendel.nodes.IndividualGenerator;
import com.andrefarzat.mendel.operators.MutationOperator;


public abstract class Mendel {
    public Node[] currentPopulation;

    public abstract int getDepth();
    public abstract int getPopulationSize();

    public abstract MutationOperator[] getMutationOperators();
    public abstract IndividualGenerator getGenerator();

    public abstract void evaluate(Node node);
    public abstract boolean shouldStop();

    public void log(String msg) {
        System.out.println(msg);
    }

    public void log(String msg, Object ...params) {
        msg = String.format(msg, params);
        this.log(msg);
    }

    public Node[] mutateCurrentPopulation() {
        int size = this.getPopulationSize();
        // 1. Ranking the current population by its measure
        Arrays.sort(this.currentPopulation);

        // 2. Getting only the half good
        Node[] currentPopulation = Arrays.copyOfRange(this.currentPopulation, 0, size / 2);

        // 3. Generating a muted generation
        ArrayList<Node> mutedGeneration = this.mutatePopulation(currentPopulation);

        // 4. Instantiating new population
        Node[] newGeneration = new Node[this.getPopulationSize()];

        // 5. Concatenating remaining individuals with mutated generation
        for(Node node : currentPopulation) {
            size --;
            newGeneration[size] = node;
        }

        for(Node node : mutedGeneration) {
            size --;
            newGeneration[size] = node;
        }

        return newGeneration;
    }

    public ArrayList<Node> mutatePopulation(Node[] population) {
        ArrayList<Node> newGeneration = new ArrayList<Node>();

        for(Node node : population) {
            MutationOperator operator = this.getMutationOperators()[0];
            operator.mutate(node);
            newGeneration.add(node);
        }

        return newGeneration;
    }

    public void run() {
        // 1. Generate initial population
        this.currentPopulation = this.getGenerator().generateInitialPopulation(this.getPopulationSize(), this.getDepth());

        while(true) {
            // 2. Evaluate all population
            for(Node node : this.currentPopulation) {
                this.evaluate(node);
            }

            // 3. Run Terminator
            if (this.shouldStop()) {
                break;
            }

            // 4. We mutate !
            this.currentPopulation = this.mutateCurrentPopulation();
        }

        System.out.println("Done!");
    }
}