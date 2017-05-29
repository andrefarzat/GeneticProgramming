package com.andrefarzat.GP.nodes;

import java.util.Random;

import com.andrefarzat.mendel.nodes.Function;


public class Operator extends Function {
    protected static final char[] operators = { '+', '-', '*', '/' };
    public char type = operators[0];

    public float getValue(float x) {
        switch(this.type) {
            case '+': return this.left.getValue(x) + this.right.getValue(x);
            case '-': return this.left.getValue(x) - this.right.getValue(x);
            case '/': return this.left.getValue(x) / this.right.getValue(x);
            case '*': return this.left.getValue(x) * this.right.getValue(x);
        }

        // Fixme: Treat the exception
        return this.left.getValue(x) + this.right.getValue(x);
    }

    public void mutate() {
        int index = (new Random()).nextInt(this.operators.length);
        this.type = this.operators[index];
    }

    public String toString() {
        return String.format("(%s %s %s)", this.left.toString(), this.type, this.right.toString());
    }

}
