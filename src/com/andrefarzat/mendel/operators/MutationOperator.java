package com.andrefarzat.mendel.operators;


import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Mendel;

public interface MutationOperator {
    Individual mutate(Mendel mendel, Individual ind);
}
