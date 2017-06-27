package com.andrefarzat.GP.operators;

import com.andrefarzat.GP.Individual;
import com.andrefarzat.GP.nodes.Function;
import com.andrefarzat.GP.nodes.Literal;

/**
 * Created by andrefarzat on 2017-06-26.
 */
public class ShrinkMutation implements MutationOperator {
    @Override
    public Individual mutate(Individual father) {
        Individual neo = father.clone();
        Function root = neo.getTree();

        if (root.getDepth() < 2) {
            // Too Small. We do nothing here
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
            double value = ((Literal) func.left).value + ((Literal) func.right).value;

            // We create the new Literal
            Literal literal = new Literal();
            literal.value = value;

            // We set it
            if (parent.left  == func) parent.left  = literal;
            if (parent.right == func) parent.right = literal;

            // Then we restart
            this.shrink(root, root);
        }
    }

}
