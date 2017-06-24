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
}
