package com.andrefarzat.GP;

import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Assert;
import org.junit.Test;

public class LiteralTest {

    @Test
    public void testCreation(){
        Literal literal = Literal.create();

        Assert.assertThat(literal.generateRandomValue(), instanceOf(double.class));
    }

    @Test
    public void testMutation() {
        Literal literal = new Literal();

        Assert.assertTrue(Utils.compareDouble(literal.value, 0.0d) == 0);
        literal.mutate();
        Assert.assertTrue(Utils.compareDouble(literal.value, 0.0d) != 0);
    }

    @Test
    public void testGenerateRandomValue() {
        Literal literal = new Literal();

        double value1 = literal.generateRandomValue();
        double value2 = literal.generateRandomValue();
        Assert.assertTrue(Utils.compareDouble(value1, value2) != 0);
    }

    @Test
    public void testToString() {
        Literal literal = new Literal();
        literal.value = 11.1d;

        Assert.assertEquals(literal.toString(), "11.10");
    }
}
