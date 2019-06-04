package it.sevenbits.formatter.io.writer;

import it.sevenbits.formatter.io.iwriter.StringWriter;
import org.junit.Assert;
import org.junit.Test;

public class StringWriterTest {

    @Test
    public void testWithNumbersAndLetters () throws Exception {
        StringWriter writer = new StringWriter();
        writer.write('a');
        writer.write('b');
        writer.write('c');
        writer.write(' ');
        writer.write('1');
        Assert.assertEquals("abc 1", writer.toString());
    }
}
