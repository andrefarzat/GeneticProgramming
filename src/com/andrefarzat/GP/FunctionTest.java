package com.andrefarzat.GP;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class FunctionTest {
    @Test
    public void testCreation() {
        Function func = Function.create(2);

        Assert.assertEquals(func.getDepth(), 2);
        Assert.assertSame(func.left, Function.class);
        Assert.assertSame(func.right, Function.class);
    }

    @Test
    public void testGetNodes() {
        Function func = new Function();
        ArrayList<Node> nodes = new ArrayList<>();

        // Setup
        Function left = Function.create(0);
        Function right = Function.create(1);

        nodes.add(left);
        nodes.add(right);

        nodes.add(left.left);
        nodes.add(left.right);
        nodes.add(right.left);
        nodes.add(right.right);

        nodes.add(((Function) right.left).left);
        nodes.add(((Function) right.left).right);
        nodes.add(((Function) right.right).left);
        nodes.add(((Function) right.right).right);

        func.left = left;
        func.right = right;

        List<Node> funcNodes = func.getNodes();
        for(Node node : nodes) {
            Assert.assertThat(funcNodes, hasItem(node));
        }

        Assert.assertEquals(funcNodes.size(), nodes.size());
    }

    @Test
    public void testMutation() {
        Function func = new Function();

        Assert.assertEquals(func.type, '|');
        func.mutate();
        Assert.assertNotNull(func.type);
    }
}
