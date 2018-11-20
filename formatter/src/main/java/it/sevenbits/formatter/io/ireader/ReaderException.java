package it.sevenbits.formatter.io.ireader;

/**
 * Can appear while reading input next
 */
public class ReaderException extends Exception {

    /**
     * Constructor without parameters
     */
    public ReaderException() {
    }

    /**
     * Constructor with massage of an exception
     * @param s Is a message about an exception
     */
    public ReaderException(final String s) {
        super(s);
    }

    /**
     * Constructor with cause of an exception
     * @param throwable Is a cause of an exception
     */
    public ReaderException(final Throwable throwable) {
        super(throwable);
    }

    /**
     * Constructor with message and cause of an exception
     * @param s Is a message about an exception
     * @param throwable Is a cause of an exception
     */
    public ReaderException(final String s, final Throwable throwable) {
        super(s, throwable);
    }

}
