package it.sevenbits.formatter.io.writer;

import it.sevenbits.formatter.io.ireader.FileReader;
import it.sevenbits.formatter.io.iwriter.FileWriter;
import it.sevenbits.formatter.io.iwriter.IWriter;
import it.sevenbits.formatter.io.iwriter.WriterException;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;

import static org.junit.Assert.*;

public class FileWriterTest {

    @Test
    public void testWithNumbersAndLetters () throws Exception {
        try (FileWriter writer = new FileWriter("out.txt", Charset.forName("UTF-8"))) {
            writer.write('1');
            writer.write('w');
            writer.write('o');
            writer.write('r');
            writer.write('d');
            writer.write('\n');
            writer.write('с');
            writer.write('л');
            writer.write('о');
            writer.write('в');
            writer.write('о');
        }
        try (FileReader reader = new FileReader("out.txt", Charset.forName("UTF-8"))) {
            StringBuilder result = new StringBuilder();
            while (reader.hasNext()) {
                result.append(reader.read());
            }
            assertEquals("1word\nслово", result.toString());
        }
    }
}