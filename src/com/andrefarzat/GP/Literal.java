package com.andrefarzat.GP;


public class Literal implements Node {
    protected double value;

    public void mutate() {
        this.value = this.generateRandomValue();
    }

    @Override
    public double getValue(double value) {
        return Utils.fixDouble(this.value);
    }

    @Override
    public Literal clone() {
        Literal literal = new Literal();
        literal.value = this.value;
        return literal;
    }

    public double generateRandomValue() {
        int first = Utils.random.nextInt(10);
        int second = Utils.random.nextInt(10) + 1;

        return Utils.fixDouble(first + "." + second);
    }

    public static Literal create() {
        Literal literal = new Literal();
        literal.mutate();
        return literal;
    }

    public String toString() {
        return String.format( "%.2f", this.value);
    }
}