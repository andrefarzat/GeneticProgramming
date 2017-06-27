package com.andrefarzat.GP.nodes;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class FunctionTest {
    @Test
    public void testCreation() {
        Function func = Function.create(2);

        Assert.assertEquals(func.getDepth(), 2);
        Assert.assertThat(func.left, instanceOf(Function.class));
        Assert.assertThat(func.right, instanceOf(Function.class));
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
    public void testGetFunctions() {
        Function func = Function.create(0);
        Assert.assertEquals(func.getFunctions().size(), 1);

        func = Function.create(1);
        Assert.assertEquals(func.getFunctions().size(), 3);

        func = Function.create(2);
        Assert.assertEquals(func.getFunctions().size(), 7);
    }

    @Test
    public void testMutation() {
        Function func = new Function();

        Assert.assertEquals(func.type, '|');
        func.mutate();
        Assert.assertNotNull(func.type);
    }

    @Test
    public void testGetFunctionParentOf() {
        Function func = new Function();

        func.left = new Function();
        ((Function)func.left).left = Literal.create();

        Node parent = func.getFunctionParentOf(((Function)func.left).left);
        Assert.assertSame(parent, func.left);
    }

    @Test
    public void testShrink() {
        Function func = new Function();
        func.type = '+';
        func.left = new Literal();
        ((Literal) func.left).value = 1;
        func.right = new Literal();
        ((Literal) func.right).value = 2;

        Assert.assertEquals(func.toString(), "(1.00 + 2.00)");
        Node node = func.shrink();
        Assert.assertEquals(node.toString(), "3.00");

        // Case 2
        func = new Function();
        func.type = '-';
        func.left = new Variable();
        func.right = new Literal();
        ((Literal) func.right).value = 2;

        Assert.assertEquals(func.toString(), "(x - 2.00)");
        node = func.shrink();
        Assert.assertEquals(node.toString(), "(x - 2.00)");

        // Case 3
        func = new Function();
        func.type = '*';

        func.left = new Function();
        ((Function) func.left).type = '+';
        ((Function) func.left).left = new Literal();
        ((Literal) ((Function) func.left).left).value = 1;
        ((Function) func.left).right = new Variable();
        Assert.assertEquals(func.left.toString(), "(1.00 + x)");

        func.right = new Literal();
        ((Literal) func.right).value = 3;
        Assert.assertEquals(func.toString(), "((1.00 + x) * 3.00)");

        node = func.shrink();
        Assert.assertEquals(node.toString(), "((1.00 + x) * 3.00)");
    }
}
