package com.andrefarzat.GP;

import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Mendel;
import com.andrefarzat.mendel.IndividualGenerator;
import com.andrefarzat.mendel.operators.CrossOperator;
import com.andrefarzat.mendel.operators.MutationOperator;
import com.andrefarzat.mendel.operators.PointMutation;
import com.andrefarzat.mendel.operators.SubtreeCrossover;


public class GP extends Mendel {
    private Generator generator = new Generator();
    private MutationOperator[] mutationOperators = new MutationOperator[] {
        new PointMutation()
    };
    private CrossOperator[] crossOperators = new CrossOperator[] {
            new SubtreeCrossover()
    };
    private float[][] params = {
            {12.0f, 13.0f},
            {14.0f, 15.0f},
    };

    public float[][] getParams() {
        return this.params;
    }

    public int getDepth() {
        return 1;
    }

    public int getPopulationSize() {
        return 4000;
    }

    public IndividualGenerator getGenerator() {
        return this.generator;
    }

    public MutationOperator[] getMutationOperators() {
        return this.mutationOperators;
    }

    public CrossOperator[] getCrossOperators() { return this.crossOperators; }

    public void evaluate(Individual ind) {
        float measure = 1000;

        for(float[] param : this.getParams()) {
            float value = ind.getValue(param[0]);

            if (value - param[1] < measure) {
                measure = Math.abs(param[1] - value);
            }

            if (Float.isInfinite(measure) || Float.isNaN(measure)) {
                System.out.println("caralho");
            }
        }

        ind.setMeasure(measure);
    }

    public boolean shouldStop() {
        for(Individual individual : this.currentPopulation.getAll()) {
            boolean isValid = true;
            for(float[] param : this.getParams()) {
                float value = individual.getValue(param[0]);
                this.log("F(%s) = %s = %s", param[0], individual.toString(), value);
                if (value != param[1]) {
                    isValid = false;
                }
            }

            if(isValid) {
                for(float[] param : this.getParams()) {
                    this.log("F(%s) = %s = %s", param[0], individual.toString(), param[1]);
                }
                this.log("Solution found! o/");
                return true;
            }
        }

        return false;
    }

}
