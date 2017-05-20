package com.andrefarzat.mendel.individuals;

public abstract class Individual {
    protected float value;
    protected float measure;

    public void setMeature(float measure) { this.measure = measure; }
    public float getMeasure() { return this.measure; }

    public float getValue(float value) {
        return this.value;
    }

    public abstract void mutate();

    public String toString() {
        return Float.toString(this.value);
    }
}