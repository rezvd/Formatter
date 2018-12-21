package it.sevenbits.formatter.formatter.state_machine;

import it.sevenbits.formatter.io.iwriter.IWriter;

public class Context {
    private int indentNumber = 0;
    private final int indentSize = 4;
    private IWriter writer;

    public Context(final IWriter writer) {
        this.writer = writer;
    }

    public int getIndentNumber() {
        return indentNumber;
    }

    public int getIndentSize() {
        return indentSize;
    }

    public IWriter getWriter() {
        return writer;
    }

    public void addIndent() {
        indentNumber++;
    }

    public void reduceIndent() {
        indentNumber--;
    }
}
