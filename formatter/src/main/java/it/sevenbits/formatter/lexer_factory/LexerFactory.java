package it.sevenbits.formatter.lexer_factory;

import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.lexer_factory.lexer.ILexer;
import it.sevenbits.formatter.lexer_factory.lexer.Lexer;

public class LexerFactory implements ILexerFactory {

    public ILexer createLexer(final IReader reader) {
        return new Lexer(reader);
    }
}
