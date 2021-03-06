package it.sevenbits.formatter;

import it.sevenbits.formatter.formatter.Formatter;
import it.sevenbits.formatter.io.ireader.FileReader;
import it.sevenbits.formatter.io.ireader.ReaderException;
import it.sevenbits.formatter.io.iwriter.FileWriter;
import it.sevenbits.formatter.io.iwriter.WriterException;
import it.sevenbits.formatter.lexer.lexer_factory.LexerFactory;

import java.io.IOException;
import java.nio.charset.Charset;

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
     * @throws ReaderException if an error appears while reading
     * @throws WriterException if an error appears while writing
     * @throws IOException if neaded streams couldn't be created
     */
    public static void main(final String[] args) throws ReaderException, WriterException, IOException {
        Formatter formatter = new Formatter(new LexerFactory());
        try (
                FileReader reader = new FileReader(args[0], Charset.forName("UTF-8"));
                FileWriter writer = new FileWriter(args[1], Charset.forName("UTF-8"))) {
            formatter.format(reader, writer);
        }
    }
}