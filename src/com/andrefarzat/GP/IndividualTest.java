package com.andrefarzat.GP;

import org.junit.Assert;
import org.junit.Test;


public class IndividualTest {

    @Test
    public void testCreation() {
        Individual individual = new Individual();

        Assert.assertNotNull(individual.tree);
        Assert.assertSame(individual.tree, Node.class);
        Assert.assertTrue(individual.isValid());
    }
}
