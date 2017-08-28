package com.andrefarzat.GP;

import com.andrefarzat.GP.nodes.Function;
import com.andrefarzat.GP.nodes.Literal;
import org.junit.Assert;
import org.junit.Test;


public class GPTest {

    @Test
    public void testGenerateIndividual() {
        String[] leftList = {"foo"};
        String[] rightList = {"bar"};
        GP gp = new GP("name", 1, leftList, rightList);

        Individual individual = gp.generateIndividual();

        Assert.assertNotNull(individual.tree);
    }

    @Test
    public void testEvaluate() {
        String[] leftList = {"foo"};
        String[] rightList = {"bar"};
        GP gp = new GP("name", 1, leftList, rightList);

        Individual individual = new Individual();

        individual.tree = new Function();
        individual.tree.type = "+";
        individual.tree.left = new Literal();
        ((Literal) individual.tree.left).value = "a";
        individual.tree.right = new Literal();
        ((Literal) individual.tree.right).value = "b";

        gp.evaluate(individual);
        Assert.assertEquals(Utils.compareDouble(individual.fitness, 2), 0);

        ((Literal) individual.tree.right).value = "abc";

        gp.evaluate(individual);
        Assert.assertEquals(Utils.compareDouble(individual.fitness, 4), 0);
    }
}
