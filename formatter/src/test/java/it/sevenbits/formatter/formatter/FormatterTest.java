package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.io.ireader.StringReader;
import it.sevenbits.formatter.io.iwriter.IWriter;
import it.sevenbits.formatter.io.iwriter.StringWriter;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FormatterTest {
    Formatter formatter;
    IReader reader;
    IWriter writer;
    String easy;
    String easyResult;
    String brackets;
    String bracketsResult;
    String manyIndents;
    String manyIndentsresult;

    @Test
    public void testEasy()  {
        reader = new StringReader(easy);
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals(easyResult, writer.toString());
    }

    @Test
    public void testBrackets()  {
        reader = new StringReader(brackets);
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals(bracketsResult, writer.toString());
    }

    @Test
    public void testManyIndents()  {
        reader = new StringReader(manyIndents);
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals(manyIndentsresult, writer.toString());
    }

    @Test
    public void testAgainFormatting() {
        reader = new StringReader(brackets);
        writer = new StringWriter();
        IWriter againWriter = new StringWriter();
        formatter.format(reader, writer);
        reader = new StringReader(brackets);
        formatter.format(reader, againWriter);
        assertEquals(bracketsResult, writer.toString());
        assertEquals(againWriter.toString(), writer.toString());
    }

    @Before
    public void setUp() {
        formatter = new Formatter();
        easy = "{aaa;{{aaa;}}}";
        easyResult = "{\n" +
                "    aaa;\n" +
                "    {\n" +
                "        {\n" +
                "            aaa;\n" +
                "        }\n" +
                "    }\n" +
                "}";
        brackets = "{{{{}}}}";
        bracketsResult = "{\n" +
                "    {\n" +
                "        {\n" +
                "            {\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        manyIndents = "package main.it.sevenbits;public class Line {private Point start, end;public                         Line(Point start,                           Point end){\n" +
            "       \n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "                                this.start = start;\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "        this.end = end;\n" +
                    "   \n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    " }\n" +
                    "\n" +
                    "    public double getLength(){\n" +
                    "        return Math.sqrt(Math.pow((end.getX() - start.getX()), 2) + Math.pow(end.getY() - start.getY(), 2));\n" +
                    "    }public Point getStart\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "() {return start;\n" +
                    "    }\n" +
                    "\n" +
                    "}";
        manyIndentsresult = "package main.it.sevenbits;\n" +
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
                "}";
    }

}
