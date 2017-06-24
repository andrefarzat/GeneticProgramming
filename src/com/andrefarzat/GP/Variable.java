package com.andrefarzat.GP;


public class Variable implements Node {

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

    public static Variable create() {
        return new Variable();
    }
}