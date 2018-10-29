package it.sevenbits.formatter.io.ireader;

/**
 * Reads char by char from the input string
 */
public class StringReader implements IReader {
    private String input;
    private int currenPosition;

    /**
     * Constructor of StringReader class
     * @param string Is an input string, which will be read char by char
     */
    public StringReader(final String string) {
        this.input = string;
        currenPosition = 0;
    }

    /**
     * Checks, if there is next char in the input string
     * @return true, if there is next char, and false, if it's not
     */
    @Override
    public boolean hasNext() {
        return currenPosition < input.length();
    }

    /**
     * Read next char from the input
     * @return next char
     */
    @Override
    public char read() {
        return input.charAt(currenPosition++);
    }
}
