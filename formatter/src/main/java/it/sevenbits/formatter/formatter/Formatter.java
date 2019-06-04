package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.token.IToken;
import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.io.ireader.ReaderException;
import it.sevenbits.formatter.io.iwriter.IWriter;
import it.sevenbits.formatter.io.iwriter.WriterException;
import it.sevenbits.formatter.lexer.lexer_factory.ILexerFactory;
import it.sevenbits.formatter.lexer.ILexer;

/**
 * Formats code with right indents and spaces
 */
public class Formatter implements IFormatter {
    private static final int INTENT_LENGTH = 4;

    private final ILexerFactory lexerFactory;

    /**
     * Creates Formatter with lexer for it
     * @param lexerFactory creates needed realisation of lexer
     */
    public Formatter(final ILexerFactory lexerFactory) {
        this.lexerFactory = lexerFactory;
    }

    /**
     * Puts right indents and spaces in the code
     *
     * @param reader gives code, that will be formatted
     * @param writer gives the result - formatted code
     * @throws ReaderException if an error appears while reading
     * @throws WriterException if an error appears while writing
     */
    public void format(final IReader reader, final IWriter writer) throws ReaderException, WriterException {
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
        for (int i = 0; i < previousToken.getLexeme().length(); i++) {
            writer.write(previousToken.getLexeme().charAt(i));
        }
        if (previousToken.getName().equals("left brace")) {
            intentCount++;
            newLine = true;
        }
        if (previousToken.getName().equals("semicolon")) {
            newLine = true;
        }
        while (lexer.hasMoreTokens()) {
            currentToken = lexer.nextToken();
            if (!currentToken.getName().equals("right brace") && newLine && !currentToken.getName().equals("whitespace")
                    && !currentToken.getName().equals("new line")) {
                writer.write('\n');
                for (int j = 0; j < INTENT_LENGTH * intentCount; j++) {
                    writer.write(' ');
                }
                newLine = false;
            }
            switch (currentToken.getName()) {
                case "left brace":
                    intentCount++;
                    for (int i = 0; i < currentToken.getLexeme().length(); i++) {
                        writer.write(currentToken.getLexeme().charAt(i));
                    }
                    newLine = true;
                    wasSpace = false;
                    break;
                case "right brace":
                    intentCount--;
                    writer.write('\n');
                    for (int j = 0; j < INTENT_LENGTH * intentCount; j++) {
                        writer.write(' ');
                    }
                    for (int i = 0; i < currentToken.getLexeme().length(); i++) {
                        writer.write(currentToken.getLexeme().charAt(i));
                    }
                    newLine = true;
                    wasSpace = false;
                    break;
                case "semicolon":
                    for (int i = 0; i < currentToken.getLexeme().length(); i++) {
                        writer.write(currentToken.getLexeme().charAt(i));
                    }
                    newLine = true;
                    wasSpace = false;
                    break;
                case "new line":
                    currentToken = previousToken;
                    if (!wasSpace) {
                        writer.write(' ');
                    }
                    wasSpace = true;
                    break;
                case "whitespace":
                    if (!wasSpace) {

                        for (int i = 0; i < currentToken.getLexeme().length(); i++) {
                            writer.write(currentToken.getLexeme().charAt(i));
                        }
                    }
                    wasSpace = true;
                    currentToken = previousToken;
                    break;
                case "comma":
                    for (int i = 0; i < currentToken.getLexeme().length(); i++) {
                        writer.write(currentToken.getLexeme().charAt(i));
                    }
                    writer.write(' ');
                    wasSpace = true;
                    break;
                case "char":
                    for (int i = 0; i < currentToken.getLexeme().length(); i++) {
                        writer.write(currentToken.getLexeme().charAt(i));
                    }
                    wasSpace = false;
                default:
                    break;
            }
            previousToken = currentToken;
        }
    }
}
