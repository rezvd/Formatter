package main.java.it.sevenbits.Formatter;

import java.io.*;

public class Formatter {

    public void format(String fileToFormat, String fileForResult){
        final int INTEND_LENGTH = 4;
        int intendCount = 0;
        char currentChar;
        char previousChar; //last significant symbol
        boolean newLine = false;
        boolean wasSpace = false;
        try {
            InputStream in = new FileInputStream(fileToFormat);
            OutputStream out = new FileOutputStream(fileForResult);
            int length = in.available();
            previousChar = (char) in.read();
            out.write(previousChar);
            for (int i = 0; i < length - 1; i++) {
                currentChar = (char) in.read();
                if (previousChar == ';' && currentChar == '}') {
                    out.write('\n');
                    for (int j = 0; j < INTEND_LENGTH * (intendCount - 1); j++) {
                        out.write(' ');
                    }
                    newLine = false;
                } else if (newLine && currentChar != ' ' && currentChar != '\n') {
                    out.write('\n');
                    for (int j = 0; j < INTEND_LENGTH * intendCount; j++) {
                        out.write(' ');
                    }
                    newLine = false;
                }
                switch (currentChar) {
                    case '{':
                        intendCount++;
                        if (!wasSpace) {
                            out.write(' ');
                        }
                        out.write(currentChar);
                        newLine = true;
                        wasSpace = false;
                        break;
                    case '}':
                        intendCount--;
                        out.write(currentChar);
                        newLine = true;
                        wasSpace = false;
                        break;
                    case ';':
                        out.write(currentChar);
                        newLine = true;
                        wasSpace = false;
                        break;
                    case '\n':
                        currentChar = previousChar;
                        if (!wasSpace) {
                            out.write(' ');
                        }
                        wasSpace = true;
                        break;
                    case ' ':
                        if (!wasSpace) {
                            out.write(currentChar);
                        }
                        wasSpace = true;
                        currentChar = previousChar;
                        break;
                    default:
                        out.write(currentChar);
                        wasSpace = false;
                }
                previousChar = currentChar;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
