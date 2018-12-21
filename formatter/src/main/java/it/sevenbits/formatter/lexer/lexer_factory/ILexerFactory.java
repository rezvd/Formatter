package it.sevenbits.formatter.lexer.lexer_factory;

import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.lexer.ILexer;

/**
 * Factory, that creates lexer object with needed realisation
 */
public interface ILexerFactory {

    /**
     * Creates lexer for the given reader
     * @param reader is the source, where lexer will read new information for analysing
     * @return created lexer for this reader
     */
    ILexer createLexer(IReader reader);
}
