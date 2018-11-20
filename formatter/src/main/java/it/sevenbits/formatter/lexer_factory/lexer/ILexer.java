package it.sevenbits.formatter.lexer_factory.lexer;

import it.sevenbits.formatter.Token.IToken;
import it.sevenbits.formatter.io.ireader.ReaderException;

public interface ILexer {

    boolean hasMoreTokens();

    IToken nextToken() throws ReaderException;
}
