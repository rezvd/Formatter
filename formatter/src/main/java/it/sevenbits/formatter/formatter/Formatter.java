package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.io.ireader.ReaderException;
import it.sevenbits.formatter.io.iwriter.IWriter;

/**
 * Formats code with right indents and spaces
 */
public class Formatter {
    private static final int INTENT_LENGTH = 4;

    /**
     * Puts right indents and spaces in the code
     *
     * @param in  Contains input code, which will be format
     * @param out Gives the result - formatted code
     * @throws ReaderException if an error appears while reading
     */
    public void format(final IReader in, final IWriter out) throws ReaderException {
        int intentCount = 0;
        char currentChar;
        char previousChar;
        boolean newLine = false;
        boolean wasSpace = false;
        if (in.hasNext()) {
            previousChar = in.read();
        } else {
            return;
        }
        out.write(previousChar);
        if (previousChar == '{' || previousChar == ';') {
            intentCount++;
            newLine = true;
        }
        while (in.hasNext()) {
            currentChar = in.read();
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
    }
}
