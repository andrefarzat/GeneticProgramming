package com.andrefarzat.GP.operators;


import com.andrefarzat.GP.Individual;

public interface MutationOperator {
    Individual mutate(Individual father);
}