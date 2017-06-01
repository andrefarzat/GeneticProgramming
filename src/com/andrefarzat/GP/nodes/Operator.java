package com.andrefarzat.GP.nodes;

import java.security.InvalidParameterException;

import com.andrefarzat.mendel.Utils;
import com.andrefarzat.mendel.nodes.Function;


public class Operator extends Function {
    protected static final char[] operators = { '+', '-', '*', '/' };
    public char type = '|';

    public double getValue(double x) throws InvalidParameterException {
        double left = this.left.getValue(x);
        double right = this.right.getValue(x);

        switch(this.type) {
            case '+': return left + right;
            case '-': return left - right;
            case '*': return left * right;
            case '/':
                // Division is the exception. We can't have zero at the right side
                if (Double.compare(right, 0d) == 0d) {
                    right = 1d;
                }

                return left / right;
        }

        throw new InvalidParameterException(String.format("Type %s doesn't exist", this.type));
    }

    public void mutate() {
        int index = Utils.random.nextInt(this.operators.length);
        this.type = this.operators[index];
    }

    public Operator clone() {
        Operator op = new Operator();
        op.left = this.left.clone();
        op.right = this.right.clone();
        op.type = this.type;
        return op;
    }

    public String toString() {
        return String.format("(%s %s %s)", this.left.toString(), this.type, this.right.toString());
    }
}
