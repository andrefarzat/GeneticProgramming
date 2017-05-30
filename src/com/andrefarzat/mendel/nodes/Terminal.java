package com.andrefarzat.mendel.nodes;


public abstract class Terminal extends Node {
    private float value;

    public void setValue(float value) { this.value = value; }
    public float getValue() { return this.value; }
    public float getValue(float value) { return this.value; }

    public String toString() {
        return Float.toString(this.value);
    }
}
