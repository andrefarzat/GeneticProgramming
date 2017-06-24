package com.andrefarzat.GP;


public interface Node {
    Node clone();
    void mutate();
    String toString();
    double getValue(double value);
}