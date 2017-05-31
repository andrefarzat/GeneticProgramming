package com.andrefarzat.mendel.operators;


import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Mendel;

import java.util.ArrayList;

public interface CrossoverOperator {
    ArrayList<Individual> cross(Mendel mendel, Individual indA, Individual indB);
}
