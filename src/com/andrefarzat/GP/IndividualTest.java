package com.andrefarzat.GP;

import com.andrefarzat.GP.nodes.Function;
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
}
