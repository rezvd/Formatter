package it.sevenbits.formatter.io.writer;

import it.sevenbits.formatter.io.ireader.FileReader;
import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.io.iwriter.FileWriter;
import it.sevenbits.formatter.io.iwriter.IWriter;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Reader;
import java.nio.charset.Charset;

import static org.junit.Assert.*;

public class FileWriterTest {

    @Test
    public void testWithNumbersAndLetters () throws Exception {
        try (IWriter writer = new FileWriter(new FileOutputStream("out.txt"), Charset.forName("UTF-8"))) {
            writer.write('1');
            writer.write(' ');
            writer.write('w');
            writer.write("слово \n word \n");
            writer.write('l');
            writer.write('i');
            writer.write('n');
            writer.write('e');
            writer.write(" \n new line");
        }
        try (IReader reader = new FileReader(new FileInputStream("out.txt"), Charset.forName("UTF-8"))) {
            StringBuilder result = new StringBuilder();
            while (reader.hasNext()) {
                result.append(reader.read());
            }
            assertEquals("1 wслово \n word \nline \n new line", result.toString());
        }
    }
}
