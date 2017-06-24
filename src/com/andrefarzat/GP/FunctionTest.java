package com.andrefarzat.GP;

import org.junit.Assert;
import org.junit.Test;


public class FunctionTest {
    @Test
    public void testCreation() {
        Function func = Function.create(2);

        Assert.assertEquals(func.getDepth(), 2);
        Assert.assertSame(func.left, Function.class);
        Assert.assertSame(func.right, Function.class);
    }
}
