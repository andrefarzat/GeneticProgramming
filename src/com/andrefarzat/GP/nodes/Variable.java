package com.andrefarzat.GP.nodes;

import com.andrefarzat.mendel.MendelValue;
import com.andrefarzat.mendel.nodes.Terminal;


public class Variable extends Terminal {

    public void mutate() {
        // pass. I don't mutate #shrug
    }

    public Variable clone() {
        return new Variable();
    }

    public MendelValue getValue(MendelValue value) { return value; }

    public String toString() {
        return "x";
    }
}
