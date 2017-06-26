package com.andrefarzat.GP;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;


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
        Individual cloned     = individual.clone();

        Assert.assertTrue(Utils.compareDouble(individual.fitness, cloned.fitness) == 0);
        Assert.assertEquals(individual.tree.toString(), cloned.tree.toString());
        Assert.assertNotEquals(individual.id, cloned.id);
    }
}
