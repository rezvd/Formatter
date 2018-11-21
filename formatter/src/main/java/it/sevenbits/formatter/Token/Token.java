package it.sevenbits.formatter.Token;

/**
 * Contains lexeme and name for it
 */
public class Token implements IToken {
    private String name = "default";
    private String lexeme = "";

    /**
     * Creates token for parameters
     * @param name is the name of the future token
     * @param lexeme if the lexeme of the future token
     */
    public Token(final String name, final String lexeme) {
        this.name = name;
        this.lexeme = lexeme;
    }

    public String getName() {
        return name;
    }

    public String getLexeme() {
        return lexeme;
    }
}
