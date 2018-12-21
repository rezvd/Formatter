package it.sevenbits.formatter.formatter.state_machine.command;

import it.sevenbits.formatter.io.iwriter.WriterException;

public interface ICommand {

    void execute(String lexeme) throws WriterException;
}
