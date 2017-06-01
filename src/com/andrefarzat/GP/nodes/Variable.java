package com.andrefarzat.GP.nodes;

import com.andrefarzat.mendel.nodes.Terminal;


public class Variable extends Terminal {

    public void mutate() {
        // pass. I don't mutate #shrug
    }

    public Variable clone() {
        return new Variable();
    }

    public double getValue(double value) { return value; }

    public String toString() {
        return "x";
    }
}
