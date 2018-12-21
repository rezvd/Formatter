package it.sevenbits.formatter.lexer.state_machine.command;

import it.sevenbits.formatter.lexer.state_machine.command.command_repository.CommandComma;
import it.sevenbits.formatter.lexer.state_machine.command.command_repository.CommandNewLine;
import it.sevenbits.formatter.lexer.state_machine.command.command_repository.CommandStringEnd;
import it.sevenbits.formatter.lexer.state_machine.command.command_repository.CommandWrite;
import it.sevenbits.formatter.lexer.state_machine.Pair;
import it.sevenbits.formatter.lexer.state_machine.command.command_repository.CommandChar;
import it.sevenbits.formatter.lexer.state_machine.command.command_repository.CommandCommentEnd;
import it.sevenbits.formatter.lexer.state_machine.command.command_repository.CommandLeftBrace;
import it.sevenbits.formatter.lexer.state_machine.command.command_repository.CommandRightBrace;
import it.sevenbits.formatter.lexer.state_machine.command.command_repository.CommandSemicolon;
import it.sevenbits.formatter.lexer.state_machine.command.command_repository.CommandStringStart;
import it.sevenbits.formatter.lexer.state_machine.command.command_repository.CommandWhitespace;
import it.sevenbits.formatter.lexer.state_machine.state.State;

import java.util.HashMap;
import java.util.Map;

public class CommandRepository {
    private Map<Pair<State, Character>, ICommand> commands;
    private State stringState = new State("string");
    private State commentState = new State("comment");

    public CommandRepository() {
        commands = new HashMap<>();

        State defaultState = new State("default");
        State commentMBState = new State("commentMB");

        commands.put(new Pair<>(defaultState, ' '), new CommandWhitespace());
        commands.put(new Pair<>(defaultState, '{'), new CommandLeftBrace());
        commands.put(new Pair<>(defaultState, '}'), new CommandRightBrace());
        commands.put(new Pair<>(defaultState, '\n'), new CommandNewLine());
        commands.put(new Pair<>(defaultState, ';'), new CommandSemicolon());
        commands.put(new Pair<>(defaultState, ','), new CommandComma());
        commands.put(new Pair<>(defaultState, '"'), new CommandStringStart());
        commands.put(new Pair<>(defaultState, '/'), new CommandWrite());

        commands.put(new Pair<>(commentMBState, ' '), new CommandWhitespace());
        commands.put(new Pair<>(commentMBState, '{'), new CommandLeftBrace());
        commands.put(new Pair<>(commentMBState, '}'), new CommandRightBrace());
        commands.put(new Pair<>(commentMBState, '\n'), new CommandNewLine());
        commands.put(new Pair<>(commentMBState, ';'), new CommandSemicolon());
        commands.put(new Pair<>(commentMBState, ','), new CommandComma());
        commands.put(new Pair<>(commentMBState, '"'), new CommandStringStart());
        commands.put(new Pair<>(commentMBState, '/'), new CommandWrite());

        commands.put(new Pair<>(commentState, '\n'), new CommandCommentEnd());
        commands.put(new Pair<>(stringState, '"'), new CommandStringEnd());
    }

    public ICommand getCommand(final State state, final char c) {
        if (state.equals(stringState) || state.equals(commentState)) {
            return commands.getOrDefault(new Pair<>(state, c), new CommandWrite());
        }
        return commands.getOrDefault(new Pair<>(state, c), new CommandChar());
    }
}
