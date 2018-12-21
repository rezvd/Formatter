package it.sevenbits.formatter.formatter.state_machine.command;

import it.sevenbits.formatter.formatter.state_machine.Context;
import it.sevenbits.formatter.formatter.state_machine.Pair;
import it.sevenbits.formatter.formatter.state_machine.command.command_repository.CommandAddIndent;
import it.sevenbits.formatter.formatter.state_machine.command.command_repository.CommandAddIndentNewLine;
import it.sevenbits.formatter.formatter.state_machine.command.command_repository.CommandIgnore;
import it.sevenbits.formatter.formatter.state_machine.command.command_repository.CommandNewLine;
import it.sevenbits.formatter.formatter.state_machine.command.command_repository.CommandReduceIndent;
import it.sevenbits.formatter.formatter.state_machine.command.command_repository.CommandWrite;
import it.sevenbits.formatter.formatter.state_machine.command.command_repository.CommandWriteWithWhitespace;
import it.sevenbits.formatter.formatter.state_machine.state.State;
import it.sevenbits.formatter.io.iwriter.IWriter;
import it.sevenbits.formatter.token.IToken;

import java.util.HashMap;
import java.util.Map;

public class CommandRepository {
    private Map<Pair<State, String>, ICommand> commands;
    private State stringState = new State("string");
    private Context context;

    public CommandRepository(final IWriter writer) {
        context = new Context(writer);
        commands = new HashMap<>();

        State defaultState = new State("default");
        State whitespaceState = new State("whitespace");
        State newLineState = new State("new line");

        commands.put(new Pair<>(defaultState, "whitespace"), new CommandWrite(context));
        commands.put(new Pair<>(defaultState, "right brace"), new CommandReduceIndent(context));
        commands.put(new Pair<>(defaultState, "left brace"), new CommandAddIndent(context));
        commands.put(new Pair<>(defaultState, "semicolon"), new CommandWrite(context));
        commands.put(new Pair<>(defaultState, "whitespace"), new CommandWrite(context));
        commands.put(new Pair<>(defaultState, "new line"), new CommandIgnore(context));
        commands.put(new Pair<>(defaultState, "char"), new CommandWrite(context));
        commands.put(new Pair<>(defaultState, "comma"), new CommandWriteWithWhitespace(context));
        commands.put(new Pair<>(defaultState, "string"), new CommandWrite(context));
        commands.put(new Pair<>(defaultState, "comment"), new CommandWrite(context));

        commands.put(new Pair<>(whitespaceState, "whitespace"), new CommandWrite(context));
        commands.put(new Pair<>(whitespaceState, "right brace"), new CommandReduceIndent(context));
        commands.put(new Pair<>(whitespaceState, "left brace"), new CommandAddIndent(context));
        commands.put(new Pair<>(whitespaceState, "semicolon"), new CommandWrite(context));
        commands.put(new Pair<>(whitespaceState, "whitespace"), new CommandIgnore(context));
        commands.put(new Pair<>(whitespaceState, "new line"), new CommandIgnore(context));
        commands.put(new Pair<>(whitespaceState, "char"), new CommandWrite(context));
        commands.put(new Pair<>(whitespaceState, "comma"), new CommandWriteWithWhitespace(context));
        commands.put(new Pair<>(whitespaceState, "string"), new CommandWrite(context));
        commands.put(new Pair<>(whitespaceState, "comment"), new CommandWrite(context));

        commands.put(new Pair<>(newLineState, "whitespace"), new CommandNewLine(context));
        commands.put(new Pair<>(newLineState, "right brace"), new CommandReduceIndent(context));
        commands.put(new Pair<>(newLineState, "left brace"), new CommandAddIndentNewLine(context));
        commands.put(new Pair<>(newLineState, "semicolon"), new CommandWrite(context));
        commands.put(new Pair<>(newLineState, "whitespace"), new CommandIgnore(context));
        commands.put(new Pair<>(newLineState, "new line"), new CommandIgnore(context));
        commands.put(new Pair<>(newLineState, "char"), new CommandNewLine(context));
        commands.put(new Pair<>(newLineState, "comma"), new CommandNewLine(context));
        commands.put(new Pair<>(newLineState, "string"), new CommandNewLine(context));
        commands.put(new Pair<>(newLineState, "comment"), new CommandNewLine(context));
    }

    public ICommand getCommand(final State state, final IToken token) {
        return commands.getOrDefault(new Pair<>(state, token.getName()), new CommandWrite(context));
    }
}
