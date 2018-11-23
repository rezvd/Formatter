package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.io.ireader.ReaderException;
import it.sevenbits.formatter.io.iwriter.IWriter;
import it.sevenbits.formatter.io.iwriter.WriterException;

/**
 * Interface for formatter, that can format any code with right indent and spaces
 */
public interface IFormatter {

    /**
     * Formats any code with right indent and spaces
     * @param reader reads code, that will be formatted
     * @param out writes formatted code
     * @throws ReaderException if an error appears while reading
     * @throws WriterException if an error appears while writing
     */
    void format(IReader reader, IWriter out) throws ReaderException, WriterException;
}
