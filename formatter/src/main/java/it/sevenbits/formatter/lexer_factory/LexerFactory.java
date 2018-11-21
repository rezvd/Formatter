package it.sevenbits.formatter.lexer_factory;

import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.lexer_factory.lexer.ILexer;
import it.sevenbits.formatter.lexer_factory.lexer.Lexer;

/**
 * Creates lexer for given reader
 */
public class LexerFactory implements ILexerFactory {

    /**
     * Creates needed lexer for this reader
     * @param reader is the source, where lexer will read new information for analysing
     * @return created lexer, which works with this reader
     */
    public ILexer createLexer(final IReader reader) {
        return new Lexer(reader);
    }
}
