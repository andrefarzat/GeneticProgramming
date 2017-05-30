package com.andrefarzat.GP;

import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Mendel;
import com.andrefarzat.mendel.IndividualGenerator;
import com.andrefarzat.mendel.operators.*;

import java.util.Scanner;


public class GP extends Mendel {
    private int loopCounter = 0;
    private Generator generator = new Generator();
    private GeneticOperator[] creatorOperators = new GeneticOperator[] {
        new CreatorOperator()
    };
    private GeneticOperator[] mutationOperators = new GeneticOperator[] {
        new PointMutation(),
        new SubtreeMutation(),
        new SizeFairSubtreeMutation()
    };
    private GeneticOperator[] crossOperators = new GeneticOperator[] {
            new SubtreeCrossover(),
    };
    private double[][] simpleExampleParams = {
            {12.0f, 13.0f},
            {14.0f, 15.0f},
    };
    private double[][] celsiusToFahrenheit = {
            {1.0f, 33.8f},
            {10.0f, 50.0f},
    };

    public double[][] getParams() {
        return this.simpleExampleParams;
//        return this.celsiusToFahrenheit;
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

    public GeneticOperator[] getCreatorOperators() { return this.creatorOperators; }

    public GeneticOperator[] getMutationOperators() {
        return this.mutationOperators;
    }

    public GeneticOperator[] getCrossOperators() { return this.crossOperators; }

    public void evaluate(Individual individual) {
        double maxMeasure = 0;

        for(double[] param : this.getParams()) {
            Value value = new Value();
            value.set(param[0]);
            value = (Value) individual.getValue(value);

            double measure = individual.getTree().getDepth() * 10;
            measure += Math.abs(value.get() - param[1]);

            if (measure > maxMeasure) {
                maxMeasure = measure;
            }

            String msg = "[Measure: %s; Depth: %s;]F(%s): %s = %s";
            this.log(msg, individual.getMeasure(), individual.getTree().getDepth(), param[0], individual.toString(), value);
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
                    this.log("F(%s) = %s = %s", param[0], individual.toString(), param[1]);
                }
                this.log("Solution found in %s generations! o/", this.loopCounter);
                return true;
            }
        }
//        Scanner scanner = new Scanner(System.in);
//        scanner.next();
        return false;
    }

}
