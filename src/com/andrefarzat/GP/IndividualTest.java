package com.andrefarzat.GP;

import com.andrefarzat.GP.nodes.Function;
import com.andrefarzat.GP.nodes.Terminal;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class IndividualTest {
    protected List<String> options = new ArrayList<>(Arrays.asList("foo"));

    @Test
    public void testCreation() {
        Individual individual = new Individual();
        Assert.assertNull(individual.tree);
    }

    @Test
    public void testClone() {
        Individual individual = new Individual();
        individual.tree       = Function.create(0, this.options);
        Individual cloned     = individual.clone();

        Assert.assertTrue(Utils.compareDouble(individual.fitness, cloned.fitness) == 0);
        Assert.assertEquals(individual.tree.toString(), cloned.tree.toString());
        Assert.assertNotEquals(individual.id, cloned.id);
    }

    @Test
    public void testToString() {
        Individual individual = new Individual();
        individual.tree       = Function.create(0, this.options);

        Assert.assertEquals(individual.toString(), individual.tree.toString());
    }

    @Test
    public void testIsValid() {
        String[] leftList = {"foo"};
        String[] rightList = {"bar"};

        Individual individual = new Individual();
        individual.tree       = Function.create(0, this.options);
        individual.tree.type = "•";
        ((Terminal) individual.tree.left).value  = "fo";
        ((Terminal) individual.tree.right).value = "o";

        Assert.assertTrue(individual.isValid(leftList, rightList));
    }
}
