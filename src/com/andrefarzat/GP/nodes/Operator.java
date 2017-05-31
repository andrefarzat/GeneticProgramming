package com.andrefarzat.GP.nodes;

import java.math.BigDecimal;
import java.util.Random;

import com.andrefarzat.GP.Value;
import com.andrefarzat.mendel.MendelValue;
import com.andrefarzat.mendel.nodes.Function;


public class Operator extends Function {
    protected static final char[] operators = { '+', '-', '*', '/' };
    public char type = '|';

    public Value getValue(MendelValue x) {
        Value left = (Value) this.left.getValue(x);
        Value right = (Value) this.right.getValue(x);

        Value ret = new Value();

        switch(this.type) {
            case '+': ret.set(left.get().add(right.get()));      break;
            case '-': ret.set(left.get().subtract(right.get())); break;
            case '*': ret.set(left.get().multiply(right.get())); break;
            case '/':
                // Division is the exception. We can't have zero at the right side
                BigDecimal leftValue = left.get();
                BigDecimal rightValue = right.get();

                BigDecimal zero = new BigDecimal("0");

                if (rightValue.compareTo(zero) == 0) {
                    right.set(1);
                    rightValue = right.get();
                }

                ret.set(leftValue.divide(rightValue, 1, BigDecimal.ROUND_DOWN));

                break;
            default:
                System.out.println(this.type);
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
        op.type = this.type;
        return op;
    }

    public String toString() {
        return String.format("(%s %s %s)", this.left.toString(), this.type, this.right.toString());
    }
}
