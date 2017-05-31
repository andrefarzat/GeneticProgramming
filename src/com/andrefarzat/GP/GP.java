package com.andrefarzat.GP;

import com.andrefarzat.GP.operators.ShrinkMutation;
import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Mendel;
import com.andrefarzat.mendel.IndividualGenerator;
import com.andrefarzat.mendel.operators.*;

import java.math.BigDecimal;


public class GP extends Mendel {
    private int loopCounter = 0;
    private Generator generator = new Generator();
    public int getLogLevel() {
        return 2;
    }
    private MutationOperator[] mutationOperators = new MutationOperator[] {
            new PointMutation(),
            new SubtreeMutation(),
            new ShrinkMutation(),
            //new SizeFairSubtreeMutation()
    };
    private CrossoverOperator[] crossOperators = new CrossoverOperator[] {
            new SubtreeCrossover(),
            new SizeFairSubtreeCrossover()
    };
    private BigDecimal[][] simpleExampleParams = {
            {new BigDecimal("12.0"), new BigDecimal("13")},
            {new BigDecimal("14.0"), new BigDecimal("15")},
    };
    private BigDecimal[][] easeExampleParams = {
            {new BigDecimal("12"), new BigDecimal("32")},
            {new BigDecimal("14"), new BigDecimal("32")},
            {new BigDecimal("120"), new BigDecimal("140")},
    };
    private BigDecimal[][] notEaseExampleParams = {
            {new BigDecimal("100.0"), new BigDecimal("200.0")},
            {new BigDecimal("350.0"), new BigDecimal("450.0")},
    };
    private BigDecimal[][] celsiusToFahrenheit = {
            {new BigDecimal("1.0"), new BigDecimal("33.8")},
            {new BigDecimal("10.0"), new BigDecimal("50.0")},
    };
    private BigDecimal[][] celsiusToKelvin = {
            {new BigDecimal("20.0"), new BigDecimal("293.15")},
            {new BigDecimal("40.0"), new BigDecimal("313.15")},
    };

    public BigDecimal[][] getParams() {
        return this.simpleExampleParams;
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
        BigDecimal maxMeasure = new BigDecimal("0");

        for(BigDecimal[] param : this.getParams()) {
            Value value = new Value();
            value.set(param[0]);
            value = (Value) individual.getValue(value);

            BigDecimal measure = (value.get().subtract(param[1])).abs();

            if (measure.compareTo(maxMeasure) > 0) {
                maxMeasure = measure;
            }

            String msg = "[Measure: %.1f; Depth: %s;]F(%s): %s = %s";
            this.log(3, msg, individual.getMeasure(), individual.getTree().getDepth(), param[0], individual.toString(), value);
        }

        individual.setMeasure(maxMeasure);
    }

    public boolean shouldStop() {
        for(Individual individual : this.currentPopulation.getAll()) {
            boolean isValid = true;
            for(BigDecimal[] param : this.getParams()) {
                Value value = new Value();
                value.set(param[0]);
                value = (Value) individual.getValue(value);

                if (value.get().compareTo(param[1]) != 0) {
                    isValid = false;
                }
            }

            if(isValid) {
                for(BigDecimal[] param : this.getParams()) {
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
