package com.andrefarzat.GP;

import com.andrefarzat.GP.individuals.Generator;
import com.andrefarzat.mendel.Mendel;
import com.andrefarzat.mendel.operators.PointMutation;


public class CLI {

    public static void main(String[] args) {
        Mendel mendel = new Mendel();

        mendel.setDepth(2);
        mendel.setPopulationSize(1000);
        mendel.setGenerator(new Generator());

        mendel.addMutationOperator(new PointMutation());

        mendel.run();
    }
}
