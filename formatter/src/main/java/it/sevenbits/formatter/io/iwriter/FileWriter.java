package it.sevenbits.formatter.io.iwriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * Class, that provides opportunity to write information to file char by char
 */
public class FileWriter implements IWriter, AutoCloseable {
    private BufferedWriter bWriter;

    /**
     * Creates FileWriter for the file
     * @param outputStream must contain file for writing
     * @param charset is an encoding of the information, which will be written
     */
    public FileWriter(final OutputStream outputStream, final Charset charset) {
        bWriter = new BufferedWriter(new OutputStreamWriter(outputStream, charset));
    }

    @Override
    public void write(final char c) throws WriterException {
        try {
            bWriter.write(c);
        } catch (IOException e) {
            throw new WriterException("Couldn't write a char in the file", e);
        }
    }

    @Override
    public void write(final String s) throws WriterException {
        try {
            bWriter.write(s);
        } catch (IOException e) {
            throw new WriterException("Couldn't write a string in the file", e);
        }
    }

    @Override
    public void close() throws IOException {
        bWriter.close();
    }
}
