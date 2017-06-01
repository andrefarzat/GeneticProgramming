package com.andrefarzat.mendel.nodes;

public interface Node {
    Node clone();
    void mutate();
    String toString();
    double getValue(double value);
}