package it.sevenbits.formatter.lexer_factory.lexer;

import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.lexer.Lexer;
import it.sevenbits.formatter.lexer.LexerSM;
import it.sevenbits.formatter.token.IToken;
import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.io.ireader.ReaderException;
import it.sevenbits.formatter.io.ireader.StringReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LexerTest {
        IReader reader;
        ILexer lexer;

        @Test
        public void testBrackets() throws ReaderException {
            reader = new StringReader("{([])}");
            lexer = new Lexer(reader);
            IToken token;
            assertTrue(lexer.hasMoreTokens());
            assertEquals("left brace", (token = lexer.nextToken()).getName());
            assertEquals("{", token.getLexeme());
            assertEquals("char", (token = lexer.nextToken()).getName());
            assertEquals("([])", token.getLexeme());
            assertEquals("right brace", (token = lexer.nextToken()).getName());
            assertEquals("}", token.getLexeme());
            assertFalse(lexer.hasMoreTokens());
        }

    @Test
    public void testSpaces() throws ReaderException {
        reader = new StringReader(" a  \n");
        lexer = new Lexer(reader);
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
    public void testWord() throws ReaderException {
        reader = new StringReader("package main.it.sevenbits;public");
        lexer = new Lexer(reader);
        IToken token;
        assertTrue(lexer.hasMoreTokens());
        assertEquals("char", (token = lexer.nextToken()).getName());
        assertEquals("package", token.getLexeme());
        assertEquals("whitespace", (token = lexer.nextToken()).getName());
        assertEquals(" ", token.getLexeme());
        assertEquals("char", (token = lexer.nextToken()).getName());
        assertEquals("main.it.sevenbits", token.getLexeme());
        assertEquals("semicolon", (token = lexer.nextToken()).getName());
        assertEquals(";", token.getLexeme());
        assertEquals("char", (token = lexer.nextToken()).getName());
        assertFalse(lexer.hasMoreTokens());
    }

    @Test
    public void testSM() throws ReaderException {
        reader = new StringReader("\"djddj\"\n\n ");
        lexer = new LexerSM(reader);
        IToken token;
        while (lexer.hasMoreTokens()) {
            token = lexer.nextToken();
            System.out.println("*" + token.getLexeme() + "* " + token.getName());
        }
    }
}
