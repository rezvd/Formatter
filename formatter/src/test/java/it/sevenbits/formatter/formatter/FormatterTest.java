package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.io.ireader.ReaderException;
import it.sevenbits.formatter.io.ireader.StringReader;
import it.sevenbits.formatter.io.iwriter.IWriter;
import it.sevenbits.formatter.io.iwriter.StringWriter;
import it.sevenbits.formatter.lexer_factory.LexerFactory;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FormatterTest {
    Formatter formatter;
    IReader reader;
    IWriter writer;
    String brackets;
    String bracketsResult;

    @Before
    public void setUp() {
        formatter = new Formatter(new LexerFactory());
    }

    @Test
    public void testWithoutSpaces() throws ReaderException {
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
    public void testBrackets() throws ReaderException {
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
    public void testManyIndents() throws ReaderException {
        reader = new StringReader("package main.it.sevenbits;public class Line {private Point start,end;public                         Line(Point start,                           Point end){\n" +
                "       \n" +
                "\n" +
                "\n" +
                "\n" +
                "                                this.start = start;\n" +
                "\n" +
                "\n" +
                "        this.end = end;\n" +
                "   \n" +
                "\n" +
                " }\n" +
                "\n" +
                "    public double getLength(){\n" +
                "        return Math.sqrt(Math.pow((end.getX() - start.getX()),2) + Math.pow(end.getY() - start.getY(),2));\n" +
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
                "    public Line(Point start, Point end){ \n" +
                "        this.start = start; \n" +
                "        this.end = end; \n" +
                "    } \n" +
                "    public double getLength(){ \n" +
                "        return Math.sqrt(Math.pow((end.getX() - start.getX()), 2) + Math.pow(end.getY() - start.getY(), 2)); \n" +
                "    }\n" +
                "    public Point getStart () {\n" +
                "        return start; \n" +
                "    } \n" +
                "}", writer.toString());
    }

    @Test
    public void testAgainFormatting() throws ReaderException {
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

}
