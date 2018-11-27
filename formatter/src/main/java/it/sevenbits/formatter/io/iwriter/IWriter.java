package it.sevenbits.formatter.io.iwriter;

/**
 * Set the way the result text will be given
 */
public interface IWriter {

    /**
     * Writes to the stream new char
     * @param c will be written as next char to the stream
     * @throws WriterException if an error appears while writing char
     */
    void write(char c) throws WriterException;
}
