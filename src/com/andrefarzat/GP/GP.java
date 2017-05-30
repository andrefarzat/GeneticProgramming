package com.andrefarzat.GP;

import com.andrefarzat.GP.nodes.Generator;
import com.andrefarzat.mendel.Mendel;
import com.andrefarzat.mendel.nodes.Node;
import com.andrefarzat.mendel.nodes.IndividualGenerator;
import com.andrefarzat.mendel.operators.MutationOperator;
import com.andrefarzat.mendel.operators.PointMutation;


public class GP extends Mendel {
    private Generator generator = new Generator();
    private MutationOperator[] mutationOperators = new MutationOperator[] {
        new PointMutation()
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
        return 10;
    }

    public IndividualGenerator getGenerator() {
        return this.generator;
    }

    public MutationOperator[] getMutationOperators() {
        return this.mutationOperators;
    }

    public void evaluate(Node node) {
        float measure = 1000;

        for(float[] param : this.getParams()) {
            float value = node.getValue(param[0]);

            if (value - param[1] < measure) {
                measure = Math.abs(param[1] - value);
            }
        }

        node.setMeature(measure);
    }

    public boolean shouldStop() {
        for(Node node : this.currentPopulation) {
            boolean isValid = true;
            for(float[] param : this.getParams()) {
                float value = node.getValue(param[0]);
                this.log("F(%s) = %s = %s", param[0], node.toString(), value);
                if (value != param[1]) {
                    isValid = false;
                }
            }

            if(isValid) {
                for(float[] param : this.getParams()) {
                    this.log("F(%s) = %s = %s", param[0], node.toString(), param[1]);
                }
                this.log("Solution found! o/");
                return true;
            }
        }

        return false;
    }

}
