package it.sevenbits.formatter.io.iwriter;

/**
 * Can appear while reading input next
 */
public class WriterException extends Exception {

    /**
     * Constructor without parameters
     */
    public WriterException() {
    }

    /**
     * Constructor with massage of an exception
     * @param s Is a message about an exception
     */
    public WriterException(final String s) {
        super(s);
    }

    /**
     * Constructor with cause of an exception
     * @param throwable Is a cause of an exception
     */
    public WriterException(final Throwable throwable) {
        super(throwable);
    }

    /**
     * Constructor with message and cause of an exception
     * @param s Is a message about an exception
     * @param throwable Is a cause of an exception
     */
    public WriterException(final String s, final Throwable throwable) {
        super(s, throwable);
    }

}
