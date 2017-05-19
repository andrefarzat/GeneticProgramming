package com.andrefarzat.GP;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.lang.String;
import java.util.Random;


public class nodes {
    /*
    protected static final char[] operators = { '+', '-', '*', '/' };
    protected static final Random random = new Random();
    protected static final int MAX_VALUE = 100;

    public interface Node {
        int getValue(int x);
        void mutate();
        String toString();
    }

    public static class Number implements Node {
        public int value;
        public boolean isX = false;


        public int getValue(int x) {
            return this.isX ? x : this.value;
        }

        public void mutate() {
            boolean shouldUpdateX = nodes.random.nextBoolean();

            if(shouldUpdateX) {
                this.isX = ! this.isX;
            } else {
                this.value = nodes.random.nextInt(100);
            }
        }

        public String toString() {
            return this.isX ? "x" : Integer.toString(this.value);
        }
    }


    public static Node generateRandomNode() {
        boolean shouldCreateAnOperator = nodes.random.nextInt(nodes.MAX_VALUE) <= 33; // % chance to be an operator
        return shouldCreateAnOperator ? nodes.generateOperator() : nodes.generateNumber();
    }

    public static Operator generateOperator() {
        int index = nodes.random.nextInt(nodes.operators.length - 1);

        nodes.Operator operator = new nodes.Operator();
        operator.type = nodes.operators[index];
        operator.left = nodes.generateNumber();
        operator.right = nodes.generateNumber();

        return operator;
    }

    public static Number generateNumber() {
        boolean shouldBeXNumber = nodes.random.nextInt(100) <= 50; // % chance to be an XNumber

        nodes.Number number = new Number();
        number.isX = shouldBeXNumber;
        number.value = nodes.random.nextInt(100);
        return number;
    }
    */
}
