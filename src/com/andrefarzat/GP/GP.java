package com.andrefarzat.GP;

import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Mendel;
import com.andrefarzat.mendel.IndividualGenerator;
import com.andrefarzat.mendel.Population;
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
    };
    private CrossoverOperator[] crossOperators = new CrossoverOperator[] {
            new SubtreeCrossover(),
            new SizeFairSubtreeCrossover()
    };
    private double[][] simpleExampleParams = {
            {12d, 13d},
            {14d, 15d},
    };
    private double[][] easeExampleParams = {
            {12d, 32d},
            {14d, 34d},
            {120d, 140d},
    };
    private double[][] notEaseExampleParams = {
            {100.0d, 200.0d},
            {350.0d, 450.0d},
    };
    private double[][] celsiusToFahrenheit = {
            {1.0d, 33.8d},
            {10.0d, 50.0d},
    };
    private double[][] celsiusToKelvin = {
            {20.0d, 293.15d},
            {40.0d, 313.15d},
    };

    public double[][] getParams() {
        return this.easeExampleParams;
    }

    public int getDepth() {
        return 0;
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

    public CrossoverOperator[] getCrossOperators() {return this.crossOperators; }

    public void evaluate(Individual individual) {
        double measure = 0d;

        for(double[] param : this.getParams()) {
            double value = individual.getValue(param[0]);

            if (Double.compare(value, 0d) < 0) {
                // Negative? We punish it with a high measure
                measure = 1000d;
            } else {
                double result = value - param[1];
                measure += Math.abs(result);
            }
        }

        individual.setMeasure(measure);
    }

    public boolean shouldStop(Population population) {
        this.log(2, "Attempt %s", ++ this.loopCounter);
        population.sortByMeasure();

        boolean isFirst = true;

        for(Individual individual : population.getAll()) {

            boolean isValid = true;
            for(double[] param : this.getParams()) {
                double value = individual.getValue(param[0]);

                this.log(isFirst ? 2 : 4, "[Measure: %.2f]F(%s): %s = %.2f", individual.getMeasure(), param[0], individual.toString(), value);

                if (Double.compare(value, param[1]) != 0) {
                    isValid = false;
                }
            }

            if(isValid) {
                for(double[] param : this.getParams()) {
                    this.log(1,"F(%s): %s = %.2f", param[0], individual.toString(), param[1]);
                }
                this.log(1,"Solution found in %s generations of %s individuals! o/", this.loopCounter, this.getPopulationSize());
                return true;
            }

            isFirst = false;
        }

        return false;
    }

}
