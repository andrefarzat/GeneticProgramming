package com.andrefarzat.GP.individuals;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import com.andrefarzat.mendel.Individual;

import java.util.Random;

public class Operator extends Individual {
    public Individual left;
    public Individual right;
    public char type;

    public float getValue(float x) {
        switch(this.type) {
            case '+': return this.left.getValue(x) + this.right.getValue(x);
            case '-': return this.left.getValue(x) - this.right.getValue(x);
            case '/': return this.left.getValue(x) / this.right.getValue(x);
            case '*': return this.left.getValue(x) * this.right.getValue(x);
        }

        throw new ValueException(String.format("Type %s doesn't exist", this.type));
    }

    public String toString() {
        return String.format("(%s %s %s)", this.left.toString(), this.type, this.right.toString());
    }

    public void mutate() {

    }
}
