package it.sevenbits.formatter.io.reader;

import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.io.ireader.StringReader;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringReaderTest {


    @Test
    public void testWithFiles () throws Exception {
        IReader reader = new StringReader("abc");
        assertTrue(reader.hasNext());
        assertEquals('a', reader.read());
        assertTrue(reader.hasNext());
        assertEquals('b', reader.read());
        assertTrue(reader.hasNext());
        assertEquals('c', reader.read());
        assertFalse(reader.hasNext());

    }
}
