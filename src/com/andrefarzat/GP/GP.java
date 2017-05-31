package com.andrefarzat.GP;

import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Mendel;
import com.andrefarzat.mendel.IndividualGenerator;
import com.andrefarzat.mendel.operators.*;


public class GP extends Mendel {
    private int loopCounter = 0;
    private Generator generator = new Generator();
    public int getLogLevel() {
        return 2;
    }
    private MutationOperator[] mutationOperators = new MutationOperator[] {
        new PointMutation(),
        new SubtreeMutation(),
        //new SizeFairSubtreeMutation()
    };
    private CrossoverOperator[] crossOperators = new CrossoverOperator[] {
            new SubtreeCrossover(),
            new SizeFairCrossover()
    };
    private double[][] simpleExampleParams = {
            {12.0f, 13.0f},
            {14.0f, 15.0f},
    };
    private double[][] easeExampleParams = {
            {12.0f, 32.0f},
            {14.0f, 34.0f},
            {120.0f, 140.0f},
    };
    private double[][] notEaseExampleParams = {
            {100.0f, 200.0f},
            {350.0f, 450.0f},
    };
    private double[][] celsiusToFahrenheit = {
            {1.0f, 33.8f},
            {10.0f, 50.0f},
    };
    private double[][] celsiusToKelvin = {
            {20.0f, 293.15f},
            {40.0f, 313.15f},
    };

    public double[][] getParams() {
        return this.easeExampleParams;
    }

    public int getDepth() {
        return 1;
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

    public CrossoverOperator[] getCrossOperators() { return this.crossOperators; }

    public void evaluate(Individual individual) {
        double maxMeasure = 0;

        for(double[] param : this.getParams()) {
            Value value = new Value();
            value.set(param[0]);
            value = (Value) individual.getValue(value);

            double measure = Math.abs(value.get() - param[1]);

            if (measure > maxMeasure) {
                maxMeasure = measure;
            }

            String msg = "[Measure: %s; Depth: %s;]F(%s): %s = %s";
            this.log(3, msg, individual.getMeasure(), individual.getTree().getDepth(), param[0], individual.toString(), value);
        }

        individual.setMeasure(maxMeasure);
    }

    public boolean shouldStop() {
        for(Individual individual : this.currentPopulation.getAll()) {
            boolean isValid = true;
            for(double[] param : this.getParams()) {
                Value value = new Value();
                value.set(param[0]);
                value = (Value) individual.getValue(value);

                if (value.get() != param[1]) {
                    isValid = false;
                }
            }

            if(isValid) {
                for(double[] param : this.getParams()) {
                    this.log(1,"F(%s) = %s = %s", param[0], individual.toString(), param[1]);
                }
                this.log(1,"Solution found in %s generations! o/", this.loopCounter);
                return true;
            }
        }
        this.log(2, "Attempt %s", ++ this.loopCounter);

        return false;
    }

}
