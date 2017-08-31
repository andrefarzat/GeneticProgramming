package com.andrefarzat.GP.nodes;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FunctionTest {
    protected List<String> options = new ArrayList<>(Arrays.asList("foo"));

    @Test
    public void testCreation() {
        Function func = Function.create(2, this.options);

        Assert.assertEquals(func.getDepth(), 2);
        Assert.assertThat(func.left, instanceOf(Function.class));
        Assert.assertThat(func.right, instanceOf(Function.class));
    }

    @Test
    public void testGetNodes() {
        Function func = new Function();
        ArrayList<Node> nodes = new ArrayList<>();

        // Setup
        Function left = Function.create(0, this.options);
        Function right = Function.create(1, this.options);

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
        for (Node node : nodes) {
            Assert.assertThat(funcNodes, hasItem(node));
        }

        Assert.assertEquals(funcNodes.size(), nodes.size());
    }

    @Test
    public void testGetFunctions() {
        Function func = Function.create(0, this.options);
        Assert.assertEquals(func.getFunctions().size(), 1);

        func = Function.create(1, this.options);
        Assert.assertEquals(func.getFunctions().size(), 3);

        func = Function.create(2, this.options);
        Assert.assertEquals(func.getFunctions().size(), 7);
    }

    @Test
    public void testMutation() {
        Function func = new Function();

        Assert.assertEquals(func.type, Function.placeholder);
        func.mutate();
        Assert.assertNotEquals(func.type, Function.placeholder);
    }

    @Test
    public void testGetFunctionParentOf() {
        Function func = new Function();

        func.left = new Function();
        ((Function) func.left).left = Terminal.create();

        Node parent = func.getFunctionParentOf(((Function) func.left).left);
        Assert.assertSame(parent, func.left);
    }

    @Test
    public void testToString() {
        Function func = new Function();
        func.type = "(•)";

        func.left = new Terminal();
        ((Terminal) func.left).value = "a";
        func.right = new Terminal();
        ((Terminal) func.right).value = "b";

        Assert.assertEquals(func.toString(), "(ab)");
    }
}
