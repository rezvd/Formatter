package it.sevenbits.formatter.lexer.lexer_factory;

import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.lexer.Lexer;
import it.sevenbits.formatter.lexer.LexerSM;

/**
 * Creates lexer for given reader
 */
public class LexerFactory implements ILexerFactory, ILexerSMFactory {

    /**
     * Creates needed lexer for this reader
     * @param reader is the source, where lexer will read new information for analysing
     * @return created lexer, which works with this reader
     */
    public ILexer createLexer(final IReader reader) {
        return new Lexer(reader);
    }

    @Override
    public ILexer createLexerSM(final IReader reader) {
        return new LexerSM(reader);
    }
}
