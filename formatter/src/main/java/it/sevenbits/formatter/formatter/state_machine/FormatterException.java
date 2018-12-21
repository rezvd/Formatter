package it.sevenbits.formatter.formatter.state_machine;

/**
 * Can appear while reading input next
 */
public class FormatterException extends Exception {

    /**
     * Constructor without parameters
     */
    public FormatterException() {
    }

    /**
     * Constructor with massage of an exception
     * @param s Is a message about an exception
     */
    public FormatterException(final String s) {
        super(s);
    }

    /**
     * Constructor with cause of an exception
     * @param throwable Is a cause of an exception
     */
    public FormatterException(final Throwable throwable) {
        super(throwable);
    }

    /**
     * Constructor with message and cause of an exception
     * @param s Is a message about an exception
     * @param throwable Is a cause of an exception
     */
    public FormatterException(final String s, final Throwable throwable) {
        super(s, throwable);
    }

}
