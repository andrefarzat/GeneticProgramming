package com.andrefarzat.mendel;

public abstract class Individual {
    protected float value;

    public float getValue(float value) {
        return this.value;
    }

    public abstract void mutate();

    public String toString() {
        return Float.toString(this.value);
    }
}