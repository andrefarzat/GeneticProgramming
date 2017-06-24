package com.andrefarzat.GP;

import org.junit.Assert;
import org.junit.Test;


public class GPTest {

    @Test
    public void testGenerateIndividual() {
        GP gp = new GP();
        Individual individual = gp.generateIndividual();

        Assert.assertNotNull(individual.tree);
        Assert.assertTrue(individual.isValid());
    }
}
