package it.sevenbits.formatter.Token;

public class Token implements IToken {
    private String name = "default";
    private String lexeme = "";

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
