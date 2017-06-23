package com.andrefarzat.GP;

import com.andrefarzat.mendel.*;
import com.andrefarzat.mendel.logging.CLILogger;
import com.andrefarzat.mendel.logging.MendelLogger;
import com.andrefarzat.mendel.operators.*;


public class GP extends Mendel {
    private Generator generator = new Generator();
    private MendelLogger logger = new CLILogger(); //

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
        return this.notEaseExampleParams;
    }

    public int getDepth() {
        return 0; //this.generationNumber == 0 ? 0 : Utils.random.nextInt(5);
    }

    public int getPopulationSize() {
        return 1000;
    }

    public IndividualGenerator getGenerator() {
        return this.generator;
    }

    public MendelLogger getLogger() {
        return this.logger;
    }

    public MutationOperator[] getMutationOperators() {
        return this.mutationOperators;
    }

    public CrossoverOperator[] getCrossOperators() {return this.crossOperators; }

    public void evaluate(Individual individual) {
        double measure = 0d;

        for(double[] param : this.getParams()) {
            double value = individual.getValue(param[0]);

            if (Utils.compareDouble(value, 0d) == -1) {
                // Negative? We punish it with a high measure
                measure = 1000d;
            } else {
                double result = value - param[1];
                measure += Math.abs(result);
            }
        }

        individual.setFitness(measure);
    }

    public boolean shouldStop(Population population) {
        this.getLogger().log( "Attempt %s", this.generationNumber);
        population.sortByFitness();

        boolean isFirst = true;

        for(Individual individual : population.getAll()) {

            boolean isValid = true;
            for(double[] param : this.getParams()) {
                double value = individual.getValue(param[0]);

                if (isFirst) {
                    this.getLogger().log("[Measure: %.2f]F(%s): %s = %.2f", individual.getFitness(), param[0], individual.toString(), value);
                }

                if (Utils.compareDouble(value, param[1]) != 0) {
                    isValid = false;
                }
            }

            if(isValid) {
                for(double[] param : this.getParams()) {
                    this.getLogger().log("F(%s): %s = %.2f", param[0], individual.toString(), param[1]);
                }
                this.getLogger().log("Solution found in %s generations of %s individuals! o/", this.generationNumber, this.getPopulationSize());
                return true;
            }

            isFirst = false;
        }

        if (this.generationNumber > 1000) {
            this.getLogger().log("Solution *NOT* found in 1000 generations. Giving up =/");
            return true;
        }

        return false;
    }

}
