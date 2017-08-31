package com.andrefarzat.GP.nodes;

import static org.hamcrest.CoreMatchers.instanceOf;

import org.junit.Assert;
import org.junit.Test;

public class TerminalTest {

    @Test
    public void testCreation(){
        Terminal terminal = Terminal.create();

        Assert.assertThat(terminal.generateRandomValue(), instanceOf(String.class));
    }

    @Test
    public void testMutation() {
        Terminal terminal = new Terminal();

        Assert.assertEquals(terminal.value, "");
        terminal.mutate();
        Assert.assertNotEquals(terminal.value, "");
    }

    @Test
    public void testGenerateRandomValue() {
        Terminal terminal = new Terminal();

        String value1 = terminal.generateRandomValue();
        String value2 = terminal.generateRandomValue();
        Assert.assertNotEquals(value1, value2);
    }

    @Test
    public void testToString() {
        Terminal terminal = new Terminal();
        terminal.value = "a";

        Assert.assertEquals(terminal.toString(), "a");
    }
}
