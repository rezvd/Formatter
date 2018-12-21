package it.sevenbits.formatter.lexer.state_machine;

import it.sevenbits.formatter.token.IToken;
import it.sevenbits.formatter.token.Token;

public class TokenBuilder {
    private String tokenName = "char";
    private StringBuilder tokenLexeme;
    private boolean isTokenReady;

    public TokenBuilder() {
        tokenLexeme = new StringBuilder();
        isTokenReady = false;
    }

    public void writeChar(final char c) {
        tokenLexeme.append(c);
    }

    public boolean isTokenReady() {
        return isTokenReady;
    }

    public void setTokenReady(final boolean isReady) {
        isTokenReady = isReady;
    }

    public IToken getToken() {
        return new Token(tokenName, tokenLexeme.toString());
    }

    public void setTokenName(final String name) {
        tokenName = name;
    }

    public void clear() {
        tokenLexeme.delete(0, tokenLexeme.length());
    }
}