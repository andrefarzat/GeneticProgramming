package com.andrefarzat.GP.nodes;


public interface Node {
    Node clone();
    void mutate();
    String toString();
    String getValue();
}