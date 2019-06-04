package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.formatter.state_machine.FormatterException;
import it.sevenbits.formatter.io.ireader.FileReader;
import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.io.ireader.ReaderException;
import it.sevenbits.formatter.io.ireader.StringReader;
import it.sevenbits.formatter.io.iwriter.FileWriter;
import it.sevenbits.formatter.io.iwriter.IWriter;
import it.sevenbits.formatter.io.iwriter.StringWriter;
import it.sevenbits.formatter.io.iwriter.WriterException;
import it.sevenbits.formatter.lexer.lexer_factory.LexerFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FormatterSMTest {
    IFormatter formatter;
    IReader reader;
    IWriter writer;

    @Before
    public void setUp() {
        formatter = new FormatterSM(new LexerFactory());
    }

    @Test
    public void testWithoutSpaces() throws ReaderException, WriterException, FormatterException {
        reader = new StringReader("{aaa;{{aaa;}}}");
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("{\n" +
                "    aaa;\n" +
                "    {\n" +
                "        {\n" +
                "            aaa;\n" +
                "        }\n" +
                "    }\n" +
                "}", writer.toString());
    }

    @Test
    public void testBrackets() throws ReaderException, WriterException, FormatterException {
        reader = new StringReader("{{{{}}}}");
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("{\n" +
                "    {\n" +
                "        {\n" +
                "            {\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}", writer.toString());
    }

    @Test
    public void testManyIndents() throws ReaderException, WriterException, FormatterException {
        reader = new StringReader("package main.it.sevenbits;public class Line {private Point start,end;public        " +
                "                 Line(Point start,                           Point end){\n" +
                "       \n" +
                "\n" +
                "\n" +
                "\n" +
                "                                this.start = start;\n" +
                "\n//LINE" +
                "\n" +
                "        this.end = end;\n" +
                "   \n" +
                "\n" +
                " }\n" +
                "\n" +
                "    public double getLength(){//LINE\n" +
                "        return Math.pow(end.getX(),2);\n" +
                "    }public Point getStart\n" +
                "\n" +
                "\n" +
                "() {return start;\n" +
                "    }\n" +
                "\n" +
                "}");
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("package main.it.sevenbits;\n" +
                "public class Line {\n" +
                "    private Point start, end;\n" +
                "    public Line(Point start, Point end){\n" +
                "        this.start = start;\n" +
                "        //LINE\n" +
                "        this.end = end;\n" +
                "    }\n" +
                "    public double getLength(){\n" +
                "        //LINE\n" +
                "        return Math.pow(end.getX(), 2);\n" +
                "    }\n" +
                "    public Point getStart() {\n" +
                "        return start;\n" +
                "    }\n" +
                "}", writer.toString());
    }

    @Test
    public void testAgainFormatting() throws ReaderException, WriterException, FormatterException {
        reader = new StringReader("{{{{}}}}");
        writer = new StringWriter();
        IWriter againWriter = new StringWriter();
        formatter.format(reader, writer);
        reader = new StringReader("{{{{}}}}");
        formatter.format(reader, againWriter);
        assertEquals("{\n" +
                "    {\n" +
                "        {\n" +
                "            {\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}", writer.toString());
        assertEquals(againWriter.toString(), writer.toString());
    }

    @Test
    public void testWithMockedFileReader() throws ReaderException, WriterException, FormatterException {
        reader = mock(FileReader.class);
        when(reader.read()).thenReturn('{', '{', '{', '}', '}', '}');
        when(reader.hasNext()).thenReturn(true, true, true, true, true, true, false);
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("{\n" +
                "    {\n" +
                "        {\n" +
                "        }\n" +
                "    }\n" +
                "}", writer.toString());
    }

    @Test
    public void testString() throws ReaderException, WriterException, FormatterException {
        reader = new StringReader("{\"andh\n  \"}");
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("{\n    \"andh\n  \"\n}", writer.toString());
    }

    @Test (expected = FormatterException.class)
    public void testWithFormatterException() throws FormatterException, ReaderException, WriterException {
        IWriter writer = mock(FileWriter.class);
        doThrow(new WriterException()).when(writer).write(anyChar());
        IReader reader = mock(FileReader.class);
        when(reader.read()).thenReturn('}');
        when(reader.hasNext()).thenReturn(true);
        formatter.format(reader, writer);
    }
}
