package it.sevenbits.formatter;

import it.sevenbits.formatter.formatter.Formatter;
import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.io.ireader.StringReader;
import it.sevenbits.formatter.io.iwriter.IWriter;
import it.sevenbits.formatter.io.iwriter.StringWriter;

/**
 * Class to demonstrate work fo Formatter
 */
public final class Main {

    /**
     * Constructor, which will never be called. Utility сlass should not be public
     */
    private Main(){}

    /**
     * The main method in Main class, demonstrates work of Formatter
     * @param args Strings, which can be set up in Configurations
     */
    public static void main(final String[] args) {
        Formatter formatter = new Formatter();
        IReader reader = new StringReader(args[0]);
        IWriter writer = new StringWriter();
        formatter.format(reader, writer);
        System.out.println(writer);
    }
}