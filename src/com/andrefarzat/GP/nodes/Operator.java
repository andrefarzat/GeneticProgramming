package com.andrefarzat.GP.nodes;

import java.util.Random;

import com.andrefarzat.GP.Value;
import com.andrefarzat.mendel.MendelValue;
import com.andrefarzat.mendel.nodes.Function;


public class Operator extends Function {
    protected static final char[] operators = { '+', '-', '*', '/' };
    public char type = operators[0];

    public Value getValue(MendelValue x) {
        Value left = (Value) this.left.getValue(x);
        Value right = (Value) this.right.getValue(x);

        Value ret = new Value();

        if (this.type == '/') {
            // Division is the exception. We can't have zero at the right side
            if (right.get() == 0) {
                right.set(1.0);
            }
        }

        switch(this.type) {
            case '+': ret.set(left.get() + right.get()); break;
            case '-': ret.set(left.get() - right.get()); break;
            case '*': ret.set(left.get() * right.get()); break;
            case '/': ret.set(left.get() / right.get()); break;
        }

        return ret;
    }

    public void mutate() {
        int index = (new Random()).nextInt(this.operators.length);
        this.type = this.operators[index];
    }

    public Operator clone() {
        Operator op = new Operator();
        op.left = this.left.clone();
        op.right = this.right.clone();
        return op;
    }

    public String toString() {
        return String.format("(%s %s %s)", this.left.toString(), this.type, this.right.toString());
    }
}
