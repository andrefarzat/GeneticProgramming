package com.andrefarzat.GP.nodes;


import com.andrefarzat.GP.Utils;

public class Literal implements Node {
    protected static final String options = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ^$.";
    protected static final int optionsLength = options.length();
    public String value = "";

    public void mutate() {
        this.value = this.generateRandomValue();
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public Literal clone() {
        Literal literal = new Literal();
        literal.value = this.value;
        return literal;
    }

    public String generateRandomValue() {
        char letter = Literal.options.charAt(Utils.random.nextInt(Literal.optionsLength));
        return Character.toString(letter);
    }

    public static Literal create() {
        Literal literal = new Literal();
        literal.mutate();
        return literal;
    }

    public String toString() {
        return this.value;
    }
}