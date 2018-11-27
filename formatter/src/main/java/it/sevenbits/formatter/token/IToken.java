package it.sevenbits.formatter.token;

/**
 * Sets the way of working with token
 */
public interface IToken {

    /**
     * Returns string, which contain lexeme of this token
     * @return lexeme of this token
     */
    String getLexeme();

    /**
     * Return string, which contains name of this token
     * @return name of this token
     */
    String getName();
}
