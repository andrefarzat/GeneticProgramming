package com.andrefarzat.mendel.nodes;


import com.andrefarzat.mendel.MendelValue;

public abstract class Terminal extends Node {
    private MendelValue value;

    public void setValue(MendelValue value) { this.value = value; }
    public MendelValue getValue() { return this.value; }
    public MendelValue getValue(MendelValue value) { return this.value; }

    public String toString() {
        return this.value.toString();
    }
}
