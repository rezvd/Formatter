package it.sevenbits.formatter.lexer_factory.lexer;

import it.sevenbits.formatter.Token.IToken;
import it.sevenbits.formatter.io.ireader.ReaderException;

/**
 * Divide text into lexemes
 */
public interface ILexer {

    /**
     * Checks, if there is any token left
     * @return true, if there if other token, and false otherwise
     */
    boolean hasMoreTokens();

    /**
     * Creates new token
     * @return created token
     * @throws ReaderException if an error appears while reading
     */
    IToken nextToken() throws ReaderException;
}
