package it.sevenbits.formatter.io.iwriter;

import java.io.IOException;

/**
 * Writes chars in the output string
 */
public class StringWriter implements IWriter {
    private StringBuilder result = new StringBuilder();

    /**
     * Writes new char in the result string
     * @param c Is will be written as next char to the stream
     */
    @Override
    public void write(final char c) {
        result.append(c);
    }

    @Override
    public void write(final String s) {
        result.append(s);
    }

    /**
     * Resurn string result of writing
     * @return string, which contains all written chars
     */
    @Override
    public String toString() {
        return result.toString();
    }

    @Override
    public void close() throws IOException {
        result.delete(0, result.length());
    }
}
