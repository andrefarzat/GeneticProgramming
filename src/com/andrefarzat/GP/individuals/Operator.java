package com.andrefarzat.GP.individuals;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import java.util.Random;

import com.andrefarzat.mendel.individuals.Function;


public class Operator extends Function {
    protected static final char[] operators = { '+', '-', '*', '/' };
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

    public void mutate() {
        int index = (new Random()).nextInt(Operator.operators.length - 1);
        this.type = Operator.operators[index];
    }

    public String toString() {
        return String.format("(%s %s %s)", this.left.toString(), this.type, this.right.toString());
    }

    public Operator generate() {
        return new Operator();
    }

}
