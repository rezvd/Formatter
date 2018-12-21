package it.sevenbits.formatter.lexer.state_machine.command;

import it.sevenbits.formatter.lexer.state_machine.TokenBuilder;

public interface ICommand {

    void execute(char c, TokenBuilder tokenBuilder);
}
