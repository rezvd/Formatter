package it.sevenbits.formatter.io.ireader;

/**
 * Set the way of reading input text
 */
public interface IReader {

    /**
     * Checks, if there is next char
     * @return true, if there is next char, and false, if it's not
     */
    boolean hasNext();

    /**
     * Read next char from the input
     * @return next char
     * @throws ReaderException if there are no any next chars
     */
    char read() throws ReaderException;
}
