package it.sevenbits.formatter.formatter.state_machine.command.command_repository;

import it.sevenbits.formatter.formatter.state_machine.Context;
import it.sevenbits.formatter.formatter.state_machine.command.ICommand;
import it.sevenbits.formatter.io.iwriter.IWriter;
import it.sevenbits.formatter.io.iwriter.WriterException;

public class CommandIgnore implements ICommand {

    public CommandIgnore(final Context context) {
    }

    public void execute(final String lexeme) throws WriterException {    }
}
