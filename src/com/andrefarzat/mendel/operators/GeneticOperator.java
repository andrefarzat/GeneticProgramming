package com.andrefarzat.mendel.operators;


import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Mendel;
import com.andrefarzat.mendel.Population;


public abstract class GeneticOperator {
    public abstract Individual create(Mendel mendel, Individual individual, Population population);
}
