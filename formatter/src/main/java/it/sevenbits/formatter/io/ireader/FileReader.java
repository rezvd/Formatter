package it.sevenbits.formatter.io.ireader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Class, that provides opportunity to read from file char by char
 */
public class FileReader implements IReader {
    private BufferedReader bReader;
    private int nextChar;

    /**
     * Creates FileReader for the file
     * @param inputStream must contain the file to read
     * @param charset is encoding of the file for proper reading
     * @throws ReaderException if an error appears while reading
     */
    public FileReader(final InputStream inputStream, final Charset charset) throws ReaderException {
        bReader = new BufferedReader(new InputStreamReader(inputStream, charset));
        try {
            nextChar = bReader.read();
        } catch (IOException e) {
            throw new ReaderException("Couldn't read first symbol from file", e);
        }
    }

    @Override
    public boolean hasNext() {
        return (nextChar != -1);
    }

    @Override
    public char read() throws ReaderException {
        int current = nextChar;
        try {
            nextChar = bReader.read();
        } catch (IOException e) {
            throw new ReaderException("Couldn't read from file", e);
        }
        return (char) current;
    }

    @Override
    public void close() throws IOException {
        bReader.close();
    }
}
