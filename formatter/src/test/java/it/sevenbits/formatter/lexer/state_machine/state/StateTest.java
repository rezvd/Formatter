package it.sevenbits.formatter.lexer.state_machine.state;

import org.junit.Assert;
import org.junit.Test;

public class StateTest {

    @Test
    public void toStringTest () {
        State state = new State("someState");
        Assert.assertEquals("someState", state.toString());
    }

    @Test
    public void equalsTest () {
        State state = new State("someState");
        Assert.assertFalse(state.equals(null));
    }

    @Test
    public void equalsSameTest () {
        State state = new State("someState");
        Assert.assertTrue(state.equals(state));
    }
}