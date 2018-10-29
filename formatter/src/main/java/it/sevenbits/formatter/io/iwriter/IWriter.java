package it.sevenbits.formatter.io.iwriter;

/**
 * Set the way the text will be given
 */
public interface IWriter {

    /**
     * Writes to the stream new char
     * @param c Is will be written as next char to the stream
     */
    void write(char c);
}
