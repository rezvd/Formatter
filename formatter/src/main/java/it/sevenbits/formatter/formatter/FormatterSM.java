package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.formatter.state_machine.FormatterException;
import it.sevenbits.formatter.formatter.state_machine.command.CommandRepository;
import it.sevenbits.formatter.formatter.state_machine.command.ICommand;
import it.sevenbits.formatter.formatter.state_machine.state.State;
import it.sevenbits.formatter.formatter.state_machine.state.StateTransitions;
import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.io.ireader.ReaderException;
import it.sevenbits.formatter.io.iwriter.IWriter;
import it.sevenbits.formatter.io.iwriter.WriterException;
import it.sevenbits.formatter.lexer.lexer_factory.LexerFactory;
import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.token.IToken;

public class FormatterSM implements IFormatter {
    private StateTransitions stateTransitions = new StateTransitions();
    private CommandRepository commandRepository;

    private final LexerFactory lexerFactory;

    /**
     * Creates Formatter with lexer for it
     * @param lexerFactory creates needed realisation of lexer
     */
    public FormatterSM(final LexerFactory lexerFactory) {
        this.lexerFactory = lexerFactory;
    }

    @Override
    public void format(final IReader reader, final IWriter out) throws FormatterException {
        commandRepository = new CommandRepository(out);
        ILexer lexer = lexerFactory.createLexerSM(reader);
        State currentState = stateTransitions.getStartState();
        while (lexer.hasMoreTokens()) {
            IToken token = null;
            try {
                token = lexer.nextToken();
            } catch (ReaderException e) {
                throw new FormatterException("Error while reading", e);
            }
            try {
                ICommand command = commandRepository.getCommand(currentState, token);
                command.execute(token.getLexeme());
            } catch (WriterException e) {
                throw new FormatterException("Error while writing", e);
            }
            currentState = stateTransitions.getNextState(currentState, token.getName());
        }
    }
}
