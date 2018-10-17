package main.java.it.sevenbits.Formatter;

import java.io.*;

public class Formatter {

    public void format(InputStream in, OutputStream out) {
        final int INTENT_LENGTH = 4;
        int intentCount = 0;
        char currentChar;
        char previousChar; //last significant symbol
        boolean newLine = false;
        boolean wasSpace = false;
        try {
            int length = in.available();
            previousChar = (char) in.read();
            out.write(previousChar);
            for (int i = 0; i < length - 1; i++) {
                currentChar = (char) in.read();
                if (currentChar != '}' && newLine && currentChar != ' ' && currentChar != '\n') {
                    out.write('\n');
                    for (int j = 0; j < INTENT_LENGTH * intentCount; j++) {
                        out.write(' ');
                    }
                    newLine = false;
                }
                switch (currentChar) {
                    case '{':
                        intentCount++;
                        out.write(currentChar);
                        newLine = true;
                        wasSpace = false;
                        break;
                    case '}':
                        intentCount--;
                        out.write('\n');
                        for (int j = 0; j < INTENT_LENGTH * intentCount; j++) {
                            out.write(' ');
                        }
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
