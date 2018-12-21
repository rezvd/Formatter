package it.sevenbits.formatter.formatter.state_machine.command.command_repository;

import it.sevenbits.formatter.formatter.state_machine.Context;
import it.sevenbits.formatter.formatter.state_machine.command.ICommand;
import it.sevenbits.formatter.io.iwriter.IWriter;
import it.sevenbits.formatter.io.iwriter.WriterException;

public class CommandAddIndent implements ICommand {
    private Context context;

    public CommandAddIndent(final Context context) {
        this.context = context;
    }

    public void execute(final String lexeme) throws WriterException {
        context.addIndent();
        IWriter writer = context.getWriter();
        for (int i = 0; i < lexeme.length(); i++) {
            writer.write(lexeme.charAt(i));
        }
    }
}
