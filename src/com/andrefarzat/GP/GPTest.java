package com.andrefarzat.GP;

import com.andrefarzat.GP.nodes.Function;
import com.andrefarzat.GP.nodes.Literal;
import com.andrefarzat.GP.nodes.Variable;
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

    @Test
    public void testEvaluate() {
        GP gp = new GP();
        Individual individual = new Individual();
        double[][] params = new double[][] { {13, 14}, {15, 16} };

        individual.tree = new Function();
        individual.tree.type = '+';
        individual.tree.left = new Variable();
        individual.tree.right = new Literal();
        ((Literal) individual.tree.right).value = 1;

        gp.evaluate(individual, params);
        Assert.assertEquals(Utils.compareDouble(individual.fitness, 0), 0);

        ((Literal) individual.tree.right).value = 10;

        gp.evaluate(individual, params);
        Assert.assertEquals(Utils.compareDouble(individual.fitness, 18), 0);
    }
}
