package it.sevenbits.formatter.io.iwriter;


import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * Class, that provides opportunity to write information to file char by char
 */
public class FileWriter implements IWriter, Closeable {
    private BufferedWriter bWriter;

    /**
     * Creates FileWriter for the file
     * @param fileName is the name of file to read information
     * @param charset is an encoding of the information, which will be written
     * @throws WriterException if file couldn't be found
     */
    public FileWriter(final String fileName, final Charset charset) throws WriterException {
        try {
            bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), charset));
        } catch (FileNotFoundException e) {
            throw new WriterException("Couldn't find file: " + fileName, e);
        }
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
    public void close() throws IOException {
        bWriter.close();
    }
}
