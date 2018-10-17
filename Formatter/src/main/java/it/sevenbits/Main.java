package main.java.it.sevenbits;

import main.java.it.sevenbits.Formatter.Formatter;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        String fileToFormat = "toFormat.java";
        String fileForResult = "result.java";
        try (OutputStream out = new FileOutputStream(fileForResult);
             InputStream in = new FileInputStream(fileToFormat)) {
            Formatter formatter = new Formatter();
            formatter.format(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
