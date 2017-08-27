package com.andrefarzat.GP.nodes;

import static org.hamcrest.CoreMatchers.instanceOf;

import com.andrefarzat.GP.Utils;
import org.junit.Assert;
import org.junit.Test;

public class LiteralTest {

    @Test
    public void testCreation(){
        Literal literal = Literal.create();

        Assert.assertThat(literal.generateRandomValue(), instanceOf(String.class));
    }

    @Test
    public void testMutation() {
        Literal literal = new Literal();

        Assert.assertEquals(literal.value, "");
        literal.mutate();
        Assert.assertNotEquals(literal.value, "");
    }

    @Test
    public void testGenerateRandomValue() {
        Literal literal = new Literal();

        String value1 = literal.generateRandomValue();
        String value2 = literal.generateRandomValue();
        Assert.assertNotEquals(value1, value2);
    }

    @Test
    public void testToString() {
        Literal literal = new Literal();
        literal.value = "a";

        Assert.assertEquals(literal.toString(), "a");
    }
}
