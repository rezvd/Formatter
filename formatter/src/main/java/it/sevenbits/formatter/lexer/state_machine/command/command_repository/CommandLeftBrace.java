package it.sevenbits.formatter.lexer.state_machine.command.command_repository;

import it.sevenbits.formatter.lexer.state_machine.command.ICommand;
import it.sevenbits.formatter.lexer.state_machine.TokenBuilder;

public class CommandLeftBrace implements ICommand {

    @Override
    public void execute(final char c, final TokenBuilder tokenBuilder) {
        tokenBuilder.clear();
        tokenBuilder.writeChar(c);
        tokenBuilder.setTokenReady(true);
        tokenBuilder.setTokenName("left brace");
    }
}