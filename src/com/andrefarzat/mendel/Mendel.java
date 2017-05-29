package com.andrefarzat.mendel;

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
        System.out.println(msg);
    }

    public Node[] mutateCurrentPopulation() {
        for(Node node : this.currentPopulation) {
            MutationOperator operator = this.getMutationOperators()[0];
            operator.mutate(node);
        }

        return this.currentPopulation;
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