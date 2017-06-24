package com.andrefarzat.GP;

import org.junit.Assert;
import org.junit.Test;


public class IndividualTest {
    @Test
    public void testIsValid() {
        Individual individual = new Individual();
        Assert.assertTrue(individual.isValid());
    }

    @Test
    public void testCreation() {
        Individual individual = new Individual();

        Assert.assertNotNull(individual.tree);
        Assert.assertSame(individual.tree, Node.class);
    }
}
