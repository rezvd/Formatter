package it.sevenbits.formatter.io.ireader;

/**
 * Can appear while reading input next
 */
public class IReaderException extends Exception {

    /**
     * Constructor without parameters
     */
    public IReaderException() {
    }

    /**
     * Constructor with massage of an exception
     * @param s Is a message about an exception
     */
    public IReaderException(final String s) {
        super(s);
    }

    /**
     * Constructor with cause of an exception
     * @param throwable Is a cause of an exception
     */
    public IReaderException(final Throwable throwable) {
        super(throwable);
    }

    /**
     * Constructor with message and cause of an exception
     * @param s Is a message about an exception
     * @param throwable Is a cause of an exception
     */
    public IReaderException(final String s, final Throwable throwable) {
        super(s, throwable);
    }

}
