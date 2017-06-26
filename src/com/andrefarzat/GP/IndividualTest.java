package com.andrefarzat.GP;

import com.andrefarzat.GP.nodes.Function;
import com.andrefarzat.GP.nodes.Literal;
import com.andrefarzat.GP.nodes.Variable;
import org.junit.Assert;
import org.junit.Test;


public class IndividualTest {

    @Test
    public void testCreation() {
        Individual individual = new Individual();

        Assert.assertNull(individual.tree);
        Assert.assertFalse(individual.isValid());
    }

    @Test
    public void testClone() {
        Individual individual = new Individual();
        individual.tree       = Function.create(0);
        Individual cloned     = individual.clone();

        Assert.assertTrue(Utils.compareDouble(individual.fitness, cloned.fitness) == 0);
        Assert.assertEquals(individual.tree.toString(), cloned.tree.toString());
        Assert.assertNotEquals(individual.id, cloned.id);
    }

    @Test
    public void testToString() {
        Individual individual = new Individual();
        individual.tree       = Function.create(0);

        Assert.assertEquals(individual.toString(), individual.tree.toString());
    }

    @Test
    public void testShrink() {
        Individual individual = new Individual();
        individual.tree       = new Function();
        individual.tree.type  = '+';

        Function left = new Function();
        ((Function) left).type = '-';
        ((Function) left).left = new Literal();
        ((Literal) ((Function) left).left).value = 2;
        ((Function) left).right = new Literal();
        ((Literal) ((Function) left).right).value = 1;

        Function right = new Function();
        ((Function) right).type = '*';
        ((Function) right).left = new Literal();
        ((Literal) ((Function) right).left).value = 3;
        ((Function) right).right = new Variable();

        individual.tree.left = left;
        individual.tree.right = right;

        Assert.assertEquals(individual.toString(), "((2.00 - 1.00) + (3.00 * x))");

        individual.shrink();

        Assert.assertEquals(individual.toString(), "(1.00 + (3.00 * x))");
    }
}
