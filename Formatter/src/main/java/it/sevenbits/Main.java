package main.java.it.sevenbits;

import main.java.it.sevenbits.Formatter.Formatter;

public class Main {

    public static void main(String[] args) {
        Formatter formatter = new Formatter();
        formatter.format("toFormat.java", "result.java");
    }
}
