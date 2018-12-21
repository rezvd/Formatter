package it.sevenbits.formatter.lexer_factory.lexer;

import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.io.ireader.ReaderException;
import it.sevenbits.formatter.io.ireader.StringReader;
import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.lexer.LexerSM;
import it.sevenbits.formatter.token.IToken;
import org.junit.Test;

import static org.junit.Assert.*;

public class LexerSMTest {
    IReader reader;
    ILexer lexer;

    @Test
    public void testBrackets() throws ReaderException {
        reader = new StringReader("{()}");
        lexer = new LexerSM(reader);
        IToken token;
        assertTrue(lexer.hasMoreTokens());
        assertEquals("left brace", (token = lexer.nextToken()).getName());
        assertEquals("{", token.getLexeme());
        assertEquals("char", (token = lexer.nextToken()).getName());
        assertEquals("(", token.getLexeme());
        assertEquals("char", (token = lexer.nextToken()).getName());
        assertEquals(")", token.getLexeme());
        assertEquals("right brace", (token = lexer.nextToken()).getName());
        assertEquals("}", token.getLexeme());
        assertFalse(lexer.hasMoreTokens());
    }

    @Test
    public void testSpaces() throws ReaderException {
        reader = new StringReader(" a  \n");
        lexer = new LexerSM(reader);
        IToken token;
        assertTrue(lexer.hasMoreTokens());
        assertEquals("whitespace", (token = lexer.nextToken()).getName());
        assertEquals(" ", token.getLexeme());
        assertEquals("char", (token = lexer.nextToken()).getName());
        assertEquals("a", token.getLexeme());
        assertEquals("whitespace", (token = lexer.nextToken()).getName());
        assertEquals(" ", token.getLexeme());
        assertEquals("whitespace", (token = lexer.nextToken()).getName());
        assertEquals(" ", token.getLexeme());
        assertEquals("new line", (token = lexer.nextToken()).getName());
        assertEquals("\n", token.getLexeme());
        assertFalse(lexer.hasMoreTokens());
    }

    @Test
    public void testChar() throws ReaderException {
        reader = new StringReader(" it");
        lexer = new LexerSM(reader);
        IToken token;
        assertTrue(lexer.hasMoreTokens());
        assertEquals("whitespace", (token = lexer.nextToken()).getName());
        assertEquals(" ", token.getLexeme());
        assertEquals("char", (token = lexer.nextToken()).getName());
        assertEquals("i", token.getLexeme());
        assertEquals("char", (token = lexer.nextToken()).getName());
        assertEquals("t", token.getLexeme());
        assertFalse(lexer.hasMoreTokens());
    }

    @Test
    public void testString() throws ReaderException {
        reader = new StringReader("a\"djddj\" ");
        lexer = new LexerSM(reader);
        IToken token;
        assertTrue(lexer.hasMoreTokens());
        assertEquals("char", (token = lexer.nextToken()).getName());
        assertEquals("a", token.getLexeme());
        assertEquals("string", (token = lexer.nextToken()).getName());
        assertEquals("\"djddj\"", token.getLexeme());
        assertEquals("whitespace", (token = lexer.nextToken()).getName());
        assertEquals(" ", token.getLexeme());
        assertFalse(lexer.hasMoreTokens());
    }

    @Test
    public void testOneLineComment() throws ReaderException {
        reader = new StringReader("//as \n");
        lexer = new LexerSM(reader);
        IToken token;
        assertTrue(lexer.hasMoreTokens());
        assertEquals("comment", (token = lexer.nextToken()).getName());
        assertEquals("//as ", token.getLexeme());
        assertFalse(lexer.hasMoreTokens());
    }
}
