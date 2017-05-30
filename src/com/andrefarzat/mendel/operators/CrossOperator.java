package com.andrefarzat.mendel.operators;

import com.andrefarzat.mendel.Individual;

import java.util.ArrayList;
import java.util.Random;


public abstract class CrossOperator {
    public abstract Individual cross(Individual indA, Individual indB);
}
