package com.andrefarzat.mendel.operators;


import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Mendel;


public abstract class GeneticOperator {
    public abstract Individual create(Mendel mendel, Individual individual);
}
