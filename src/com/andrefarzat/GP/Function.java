package com.andrefarzat.GP;


public class Function implements Node {
    protected static final char[] operators = { '+', '-', '*', '/' };
    public char type = '|';

    protected Node left;
    protected Node right;


    @Override
    public Function clone() {
        Function func = new Function();
        func.left = this.left.clone();
        func.right = this.right.clone();
        func.type = this.type;
        return func;
    }

    public String toString() {
        return String.format("(%s %s %s)", this.left.toString(), this.type, this.right.toString());
    }

    @Override
    public void mutate() {
        int index = Utils.random.nextInt(this.operators.length);
        this.type = this.operators[index];
    }


    @Override
    public double getValue(double x) {
        double left = this.left.getValue(x);
        double right = this.right.getValue(x);

        switch(this.type) {
            case '+': return left + right;
            case '-': return left - right;
            case '*': return left * right;
        }

        // Division is the exception. We can't have zero at the right side
        if (Utils.compareDouble(right, 0d) == 0d) {
            right = 1;
        }

        return left / right;
    }
}
