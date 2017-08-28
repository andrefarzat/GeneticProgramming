package com.andrefarzat.GP;

import com.andrefarzat.GP.nodes.Function;
import com.andrefarzat.GP.nodes.Literal;
import org.junit.Assert;
import org.junit.Test;


public class IndividualTest {

    @Test
    public void testCreation() {
        Individual individual = new Individual();
        Assert.assertNull(individual.tree);
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
    public void testIsValid() {
        String[] leftList = {"foo"};
        String[] rightList = {"bar"};

        Individual individual = new Individual();
        individual.tree       = Function.create(0);
        individual.tree.type = "•";
        ((Literal) individual.tree.left).value  = "fo";
        ((Literal) individual.tree.right).value = "o";

        Assert.assertTrue(individual.isValid(leftList, rightList));
    }
}
