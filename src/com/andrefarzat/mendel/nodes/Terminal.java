package com.andrefarzat.mendel.nodes;


public abstract class Terminal implements Node {
    private double value;

    public void setValue(double value) { this.value = value; }
    public double getValue() { return value; }
    public double getValue(double value) { return value; }

    public abstract Terminal clone();

    public String toString() {
        return String.format( "%.2f", this.value);
    }
}
