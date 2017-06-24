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

        Assert.assertNull(literal.value);
        literal.mutate();
        Assert.assertNotNull(literal.value);
    }

    @Test
    public void testGenerateRandomValue() {
        Literal literal = new Literal();

        double value1 = literal.generateRandomValue();
        double value2 = literal.generateRandomValue();
        Assert.assertTrue(Utils.compareDouble(value1, value2) != 0);
    }
}
