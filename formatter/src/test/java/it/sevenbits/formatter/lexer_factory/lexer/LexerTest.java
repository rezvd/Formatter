package it.sevenbits.formatter.lexer_factory.lexer;

import it.sevenbits.formatter.token.IToken;
import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.io.ireader.ReaderException;
import it.sevenbits.formatter.io.ireader.StringReader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LexerTest {
        IReader reader;
        ILexer lexer;

        @Before
        public void setUp() {
        }

        @Test
        public void testBrackets() throws ReaderException {
            reader = new StringReader("{([])}");
            lexer = new Lexer(reader);
            IToken token;
            assertTrue(lexer.hasMoreTokens());
            assertEquals("Left brace", (token = lexer.nextToken()).getName());
            assertEquals("{", token.getLexeme());
            assertEquals("Left parenthesis", (token = lexer.nextToken()).getName());
            assertEquals("(", token.getLexeme());
            assertEquals("Left bracket", (token = lexer.nextToken()).getName());
            assertEquals("[", token.getLexeme());
            assertEquals("Right bracket", (token = lexer.nextToken()).getName());
            assertEquals("]", token.getLexeme());
            assertEquals("Right parenthesis", (token = lexer.nextToken()).getName());
            assertEquals(")", token.getLexeme());
            assertEquals("Right brace", (token = lexer.nextToken()).getName());
            assertEquals("}", token.getLexeme());
            assertFalse(lexer.hasMoreTokens());
        }

    @Test
    public void testSpaces() throws ReaderException {
        reader = new StringReader(" a  \n\n ");
        lexer = new Lexer(reader);
        IToken token;
        assertTrue(lexer.hasMoreTokens());
        assertEquals("Whitespace", (token = lexer.nextToken()).getName());
        assertEquals(" ", token.getLexeme());
        assertEquals("Word", (token = lexer.nextToken()).getName());
        assertEquals("a", token.getLexeme());
        assertEquals("Whitespace", (token = lexer.nextToken()).getName());
        assertEquals(" ", token.getLexeme());
        assertEquals("Whitespace", (token = lexer.nextToken()).getName());
        assertEquals(" ", token.getLexeme());
        assertEquals("New line", (token = lexer.nextToken()).getName());
        assertEquals("\n", token.getLexeme());
        assertEquals("New line", (token = lexer.nextToken()).getName());
        assertEquals("\n", token.getLexeme());
        assertEquals("Whitespace", (token = lexer.nextToken()).getName());
        assertEquals(" ", token.getLexeme());
        assertFalse(lexer.hasMoreTokens());
    }

    @Test
    public void testWord() throws ReaderException {
        reader = new StringReader("package main.it.sevenbits;public class Line ");
        lexer = new Lexer(reader);
        IToken token;
        assertTrue(lexer.hasMoreTokens());
        assertEquals("Word", (token = lexer.nextToken()).getName());
        assertEquals("package", token.getLexeme());
        assertEquals("Whitespace", (token = lexer.nextToken()).getName());
        assertEquals(" ", token.getLexeme());
        assertEquals("Word", (token = lexer.nextToken()).getName());
        assertEquals("main.it.sevenbits", token.getLexeme());
        assertEquals("Semicolon", (token = lexer.nextToken()).getName());
        assertEquals(";", token.getLexeme());
        assertEquals("Word", (token = lexer.nextToken()).getName());
        assertEquals("public", token.getLexeme());
        assertEquals("Whitespace", (token = lexer.nextToken()).getName());
        assertEquals(" ", token.getLexeme());
        assertEquals("Word", (token = lexer.nextToken()).getName());
        assertEquals("class", token.getLexeme());
        assertEquals("Whitespace", (token = lexer.nextToken()).getName());
        assertEquals(" ", token.getLexeme());
        assertEquals("Word", (token = lexer.nextToken()).getName());
        assertEquals("Line", token.getLexeme());
        assertEquals("Whitespace", (token = lexer.nextToken()).getName());
        assertEquals(" ", token.getLexeme());
        assertFalse(lexer.hasMoreTokens());
    }

}
