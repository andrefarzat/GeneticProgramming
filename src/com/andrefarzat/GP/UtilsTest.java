package com.andrefarzat.GP;

import org.junit.Assert;
import org.junit.Test;


public class UtilsTest {

    @Test
    public void testFixDouble() {
        Assert.assertTrue(Utils.fixDouble(1.001d) == Utils.fixDouble(1.001d));
        Assert.assertTrue(Utils.fixDouble("1.1") == Utils.fixDouble(1.1d));
    }

    @Test
    public void testCompareDouble() {
        Assert.assertTrue(Utils.compareDouble(1.01d, 1.00d) == 1);
        Assert.assertTrue(Utils.compareDouble(0.99d, 0.99d) == 0);
        Assert.assertTrue(Utils.compareDouble(0.99d, 1.01d) == -1);
    }
}
