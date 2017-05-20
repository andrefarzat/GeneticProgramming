package com.andrefarzat.GP;

import com.andrefarzat.GP.individuals.Generator;
import com.andrefarzat.mendel.Mendel;
import com.andrefarzat.mendel.individuals.Individual;
import com.andrefarzat.mendel.individuals.IndividualGenerator;
import com.andrefarzat.mendel.operators.MutationOperator;
import com.andrefarzat.mendel.operators.PointMutation;


public class GP extends Mendel {
    private Generator generator = new Generator();
    private MutationOperator[] mutationOperators = new MutationOperator[] {
        new PointMutation()
    };
    private float[][] params = {
            {12, 13},
            {14, 15},
    };

    public float[][] getParams() {
        return this.params;
    }

    public int getDepth() {
        return 3;
    }

    public int getPopulationSize() {
        return 1000;
    }

    public IndividualGenerator getGenerator() {
        return this.generator;
    }

    public MutationOperator[] getMutationOperators() {
        return this.mutationOperators;
    }

    public void evaluate(Individual individual) {
        for(float[] param : this.getParams()) {
            float value = individual.getValue(param[0]);
            individual.setMeature(value - param[1]);
        }
    }

    public boolean shouldStop() {
        return true;
    }

}
