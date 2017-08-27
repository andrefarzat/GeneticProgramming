package com.andrefarzat.GP.nodes;


import com.andrefarzat.GP.Utils;

import java.util.LinkedList;
import java.util.List;

public class Function implements Node {
    protected static final String placeholder = "•";
    protected static final String[] options = {"•", "•*+", "•++", "•?+", "•{•,•}+", "(•)", "[•]", "[^•]"};
    public String type = placeholder;

    public Node left;
    public Node right;

    public static Function create(int depth) {
        Function func = new Function();
        func.mutate();

        if (depth <= 0) {
            func.left = Literal.create();
            func.right = Literal.create();
        } else {
            func.left = Function.create(depth - 1);
            func.right = Function.create(depth - 1);
        }

        return func;
    }

    public List<Node> getNodes() {
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(this.left);
        nodes.add(this.right);

        if (this.left instanceof Function) {
            nodes.addAll(((Function) this.left).getNodes());
        }

        if (this.right instanceof Function) {
            nodes.addAll(((Function) this.right).getNodes());
        }

        return nodes;
    }

    public List<Function> getFunctions() {
        LinkedList<Function> funcs = new LinkedList<>();
        funcs.add(this);

        if (this.left instanceof Function) {
            funcs.addAll(((Function) this.left).getFunctions());
        }

        if (this.right instanceof Function) {
            funcs.addAll(((Function) this.right).getFunctions());
        }

        return funcs;
    }

    public int getDepth() {
        int maxDepth = 0;

        for(Node node : this.getNodes()) {
            int depth = 0;

            if (node instanceof Function) {
                depth = ((Function) node).getDepth() + 1;
            }

            if (depth > maxDepth) {
                maxDepth = depth;
            }
        }

        return maxDepth;
    }


    @Override
    public Function clone() {
        Function func = new Function();
        func.left = this.left.clone();
        func.right = this.right.clone();
        func.type = this.type;
        return func;
    }

    @Override
    public String toString() {
        try {
            return String.format("(%s %s %s)", this.left.toString(), this.type, this.right.toString());
        } catch(NullPointerException e) {
            return "No left or no right";
        }
    }

    @Override
    public void mutate() {
        int index = Utils.random.nextInt(this.options.length);
        this.type = this.options[index];
    }


    @Override
    public String getValue() {
        String left = this.left.getValue();
        String right = this.right.getValue();

        return left + right;
    }

    public Function getFunctionParentOf(Node node) {
        List<Function> funcs = this.getFunctions();
        funcs.add(0, this);

        for(Function func : funcs) {
            if (func.left == node || func.right == node) {
                return func;
            }
        }

        return null;
    }
}
