package it.sevenbits.formatter.lexer_factory.lexer;

import it.sevenbits.formatter.token.IToken;
import it.sevenbits.formatter.token.Token;
import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.io.ireader.ReaderException;

/**
 * Divides given text into meaningful parts - lexemes, which are stored in tokens
 */
public class Lexer implements ILexer {
    private IReader reader;
    private char previous = ' ';
    private boolean hasPrevious = false;

    /**
     * Creates lexer for this Reader
     * @param reader gives data for lexer
     */
    public Lexer(final IReader reader) {
        this.reader = reader;
    }

    @Override
    public boolean hasMoreTokens() {
        return reader.hasNext();
    }

    @Override
    public IToken nextToken() throws ReaderException {
        char c = ' ';
        if (hasPrevious) {
            c = previous;
            hasPrevious = false;
        } else {
            c = reader.read();
        }
        String name;
        StringBuilder lexeme = new StringBuilder();
        switch (c) {
            case '(':
                name = "Left parenthesis";
                lexeme.append(c);
                break;
            case ')':
                name = "Right parenthesis";
                lexeme.append(c);
                break;
            case '[':
                name = "Left bracket";
                lexeme.append(c);
                break;
            case ']':
                name = "Right bracket";
                lexeme.append(c);
                break;
            case '{':
                name = "Left brace";
                lexeme.append(c);
                break;
            case '}':
                name = "Right brace";
                lexeme.append(c);
                break;
            case ' ':
                name = "Whitespace";
                lexeme.append(c);
                break;
            case '\n':
                name = "New line";
                lexeme.append(c);
                break;
            case ';':
                name = "Semicolon";
                lexeme.append(c);
                break;
            case ',':
                name = "Comma";
                lexeme.append(c);
                break;
            default:
                name = "Word";
                while (c != '(' && c != ')' && c != '[' && c != ']' && c != '{' && c != '}' && c != ' ' && c != '\n'
                        && c != ';' && c != ',') {
                    lexeme.append(c);
                    if (reader.hasNext()) {
                        c = reader.read();
                    } else {
                        break;
                    }
                }
                if (c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}' || c == ' ' || c == '\n'
                        || c == ';' || c == ',') {
                    previous = c;
                    hasPrevious = true;
                }
                break;
        }
        return new Token(name, lexeme.toString());
    }
}
