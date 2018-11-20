package it.sevenbits.formatter.lexer_factory;

import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.lexer_factory.lexer.ILexer;

public interface ILexerFactory {

    ILexer createLexer(IReader reader);
}
