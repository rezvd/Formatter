package it.sevenbits.formatter.lexer.state_machine.command.command_repository;

import it.sevenbits.formatter.lexer.state_machine.TokenBuilder;
import it.sevenbits.formatter.lexer.state_machine.command.ICommand;

public class CommandCommentEnd implements ICommand {

    @Override
    public void execute(final char c, final TokenBuilder tokenBuilder) {
        tokenBuilder.setTokenReady(true);
        tokenBuilder.setTokenName("comment");
    }
}
