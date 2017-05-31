package com.andrefarzat.GP.operators;

import com.andrefarzat.GP.Value;
import com.andrefarzat.GP.nodes.Literal;
import com.andrefarzat.mendel.Individual;
import com.andrefarzat.mendel.Mendel;
import com.andrefarzat.mendel.nodes.Function;
import com.andrefarzat.mendel.operators.MutationOperator;


public class ShrinkMutation implements MutationOperator {
    public Individual mutate(Mendel mendel, Individual individual) {
        Individual neo = individual.clone();
        Function root  = neo.getTree();

        if (root.getDepth() < 2) {
            // Too small. We do nothing here
            return neo;
        }

        this.shrink(root, root);
        return neo;
    }

    private void shrink(Function func, Function root) {
        if (func.left  instanceof Function) this.shrink((Function) func.left, root);
        if (func.right instanceof Function) this.shrink((Function) func.right, root);

        if (func.left instanceof Literal && func.right instanceof Literal) {
            // We do the shrink o/
            Function parent = root.getFunctionParentOf(func);

            if (parent == null) {
                // Well ... we can't change the root
                return;
            }

            // We get the computed value
            Value value = new Value();
            value.set(0.0);
            value.set(((Value) func.left.getValue(value)).get().add(((Value) func.right.getValue(value)).get()));

            // We create the new Literal
            Literal literal = new Literal();
            literal.setValue(value);

            // We set it
            if (parent.left  == func) parent.left  = literal;
            if (parent.right == func) parent.right = literal;

            // Then we restart
            this.shrink(root, root);
        }
    }

}
