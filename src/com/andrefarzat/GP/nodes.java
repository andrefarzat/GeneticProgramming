package com.andrefarzat.GP;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.lang.String;
import java.util.Random;


public class nodes {
    public static final char[] operators = { '+', '-', '*', '/' };
    protected static final Random random = new Random();

    public static abstract class Node {
        public abstract int getValue();
        public abstract void mutate(int x);

        public String toString() {
            return Integer.toString(this.getValue());
        }
    }

    public static class Number extends Node {
        public int value;

        public int getValue() {
            return this.value;
        }

        public void mutate(int x) {
            this.value = nodes.random.nextInt(100);
        }
    }

    public static class XNumber extends Number {
        public String toString() {
            return "x";
        }

        public void mutate() {
            // pass
        }
    }

    public static class Operator extends Node {
        public Node left;
        public Node right;
        public char type;

        public int getValue() {
            switch(this.type) {
                case '+': return this.left.getValue() + this.right.getValue();
                case '-': return this.left.getValue() - this.right.getValue();
                case '/': return this.left.getValue() / this.right.getValue();
                case '*': return this.left.getValue() * this.right.getValue();
            }

            throw new ValueException(String.format("Type %s doesn't exist", this.type));
        }

        public String toString() {
            return String.format("(%s %s %s)", this.left.toString(), this.type, this.right.toString());
        }

        public void mutate(int x) {
            boolean shouldMutateLeft = nodes.random.nextBoolean();

            if (shouldMutateLeft) {
                this.left = nodes.generateRandomNode(x);
            } else {
                this.right = nodes.generateRandomNode(x);
            }
        }
    }

    public static Node generateRandomNode(int x) {
        boolean shouldCreateAnOperator = nodes.random.nextInt(100) <= 33; // 33% chance to be an operator
        return shouldCreateAnOperator ? nodes.generateOperator(x) : nodes.generateNumber(x);
    }

    public static Operator generateOperator(int x) {
        int index = nodes.random.nextInt(3);

        nodes.Operator operator = new nodes.Operator();
        operator.type = nodes.operators[index];
        operator.left = nodes.generateNumber(x);
        operator.right = nodes.generateNumber(x);

        return operator;
    }

    public static Number generateNumber(int x) {
        boolean shouldBeXNumber = nodes.random.nextInt(100) <= 33; // 33% chance to be an XNumber
        nodes.Number number;

        if (shouldBeXNumber) {
            number = new nodes.XNumber();
            number.value = x;
        } else {
            number = new nodes.Number();
            number.value = nodes.random.nextInt(100);
        }

        return number;
    }
}
