package com.andrefarzat.mendel.nodes;

public abstract class Node {
    public abstract float getValue(float value);

    public abstract Node clone();

    public abstract void mutate();

    public abstract String toString();
}