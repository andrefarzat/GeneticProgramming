package com.andrefarzat.mendel.nodes;

public abstract class Node implements Comparable<Node> {
    protected float value;
    protected float measure;

    public void setMeature(float measure) { this.measure = measure; }
    public float getMeasure() { return this.measure; }

    public float getValue(float value) {
        return this.value;
    }

    public abstract void mutate();
    public abstract Node clone();

    public String toString() {
        return Float.toString(this.value);
    }

    public int compareTo(Node node) {
        return Float.floatToIntBits(this.getMeasure() - node.getMeasure());
    }
}