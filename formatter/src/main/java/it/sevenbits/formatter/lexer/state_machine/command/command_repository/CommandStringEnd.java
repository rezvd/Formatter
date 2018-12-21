package it.sevenbits.formatter.lexer.state_machine.command.command_repository;

import it.sevenbits.formatter.lexer.state_machine.TokenBuilder;
import it.sevenbits.formatter.lexer.state_machine.command.ICommand;

public class CommandStringEnd implements ICommand {

    @Override
    public void execute(final char c, final TokenBuilder tokenBuilder) {
        tokenBuilder.writeChar(c);
        tokenBuilder.setTokenReady(true);
        tokenBuilder.setTokenName("string");
    }
}
