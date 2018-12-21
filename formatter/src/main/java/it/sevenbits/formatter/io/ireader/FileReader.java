package it.sevenbits.formatter.io.ireader;


import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Class, that provides opportunity to read from file char by char
 */
public class FileReader implements IReader, Closeable {
    private BufferedReader bReader;
    private int nextChar;

    /**
     * Creates FileReader for the file
     * @param fileName if the name of file, where information will be read
     * @param charset is encoding of the file for proper reading
     * @throws ReaderException if an error appears while reading
     */
    public FileReader(final String fileName, final Charset charset) throws ReaderException {
        try {
            bReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), charset));
        } catch (FileNotFoundException e) {
            throw new ReaderException("Couldn't find file: " + fileName, e);
        }
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
