package it.sevenbits.formatter.lexer.state_machine;

import it.sevenbits.formatter.formatter.state_machine.Pair;
import org.junit.Assert;
import org.junit.Test;

public class PairTest {

    @Test
    public void equalsNullTest() {
        Pair<String, String> pair = new Pair<>("string1", "string2");
        Assert.assertFalse(pair.equals(null));
    }

    @Test
    public void equalsSameTest () {
        Pair<String, String> pair = new Pair<>("string1", "string2");
        Assert.assertTrue(pair.equals(pair));
    }
}