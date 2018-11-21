package it.sevenbits.formatter.io.iwriter;

import java.io.Closeable;

/**
 * Set the way the result text will be given
 */
public interface IWriter extends Closeable {

    /**
     * Writes to the stream new char
     * @param c will be written as next char to the stream
     * @throws WriterException if an error appears while writing char
     */
    void write(char c) throws WriterException;

    /**
     * Writes to the stream new string
     * @param s will be written in the end of the stream
     * @throws WriterException if an error appears while writing string
     */
    void write(String s) throws WriterException;
}
