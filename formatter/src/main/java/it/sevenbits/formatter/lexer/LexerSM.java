package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.io.ireader.ReaderException;
import it.sevenbits.formatter.lexer.state_machine.TokenBuilder;
import it.sevenbits.formatter.lexer.state_machine.command.CommandRepository;
import it.sevenbits.formatter.lexer.state_machine.state.State;
import it.sevenbits.formatter.lexer.state_machine.state.StateTransitions;
import it.sevenbits.formatter.token.IToken;

public class LexerSM implements ILexer {
    private IReader reader;
    private CommandRepository commandRepository;
    private StateTransitions stateTransitions = new StateTransitions();
    private State currentState;

    public LexerSM(final IReader reader) {
        this.reader = reader;
        commandRepository = new CommandRepository();
        currentState = stateTransitions.getStartState();
    }

    @Override
    public boolean hasMoreTokens() {
        return reader.hasNext();
    }

    @Override
    public IToken nextToken() throws ReaderException {
        char currentChar;
        TokenBuilder tokenBuilder = new TokenBuilder();
        do {
            currentChar = reader.read();
            commandRepository.getCommand(currentState, currentChar).execute(currentChar,  tokenBuilder);
            currentState = stateTransitions.getNextState(currentState, currentChar);

        } while (!tokenBuilder.isTokenReady() && reader.hasNext());
        return tokenBuilder.getToken();
    }
}
