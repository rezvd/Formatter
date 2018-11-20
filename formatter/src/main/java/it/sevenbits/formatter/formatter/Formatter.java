package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.Token.IToken;
import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.io.ireader.ReaderException;
import it.sevenbits.formatter.io.iwriter.IWriter;
import it.sevenbits.formatter.lexer_factory.ILexerFactory;
import it.sevenbits.formatter.lexer_factory.lexer.ILexer;

/**
 * Formats code with right indents and spaces
 */
public class Formatter {
    private static final int INTENT_LENGTH = 4;

    private final ILexerFactory lexerFactory;

    public Formatter(final ILexerFactory lexerFactory) {
        this.lexerFactory = lexerFactory;
    }
    /**
     * Puts right indents and spaces in the code
     *
     * @param out gives the result - formatted code
     * @throws ReaderException if an error appears while reading
     */
    public void format(final IReader reader, final IWriter out) throws ReaderException {
        ILexer lexer = lexerFactory.createLexer(reader);
        int intentCount = 0;
        IToken currentToken;
        IToken previousToken;
        boolean newLine = false;
        boolean wasSpace = false;
        if (lexer.hasMoreTokens()) {
            previousToken = lexer.nextToken();
        } else {
            return;
        }
        out.write(previousToken.getLexeme());
        if (previousToken.getName().equals("Left brace")) {
            intentCount++;
            newLine = true;
        }
        if (previousToken.getName().equals("Semicolon")) {
            newLine = true;
        }
        while (lexer.hasMoreTokens()) {
            currentToken = lexer.nextToken();
            if (!currentToken.getName().equals("Right brace") && newLine && !currentToken.getName().equals("Whitespace")
                    && !currentToken.getName().equals("New line")) {
                out.write('\n');
                for (int j = 0; j < INTENT_LENGTH * intentCount; j++) {
                    out.write(' ');
                }
                newLine = false;
            }
            switch (currentToken.getName()) {
                case "Left brace":
                    intentCount++;
                    out.write(currentToken.getLexeme());
                    newLine = true;
                    wasSpace = false;
                    break;
                case "Right brace":
                    intentCount--;
                    out.write('\n');
                    for (int j = 0; j < INTENT_LENGTH * intentCount; j++) {
                        out.write(' ');
                    }
                    out.write(currentToken.getLexeme());
                    newLine = true;
                    wasSpace = false;
                    break;
                case "Semicolon":
                    out.write(currentToken.getLexeme());
                    newLine = true;
                    wasSpace = false;
                    break;
                case "New line":
                    currentToken = previousToken;
                    if (!wasSpace) {
                        out.write(' ');
                    }
                    wasSpace = true;
                    break;
                case "Whitespace":
                    if (!wasSpace) {
                        out.write(currentToken.getLexeme());
                    }
                    wasSpace = true;
                    currentToken = previousToken;
                    break;
                case "Comma":
                    out.write(currentToken.getLexeme());
                    out.write(' ');
                    wasSpace = true;
                    break;
                case "Word":
                case "Left parenthesis":
                case "Right parenthesis":
                case "Left bracket":
                case "Right bracket":
                    out.write(currentToken.getLexeme());
                    wasSpace = false;
                default:
                    break;
            }
            previousToken = currentToken;
        }
    }
}
