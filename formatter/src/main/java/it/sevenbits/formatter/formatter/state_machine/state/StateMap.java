package it.sevenbits.formatter.formatter.state_machine.state;

import it.sevenbits.formatter.lexer.state_machine.Pair;

import java.util.HashMap;
import java.util.Map;

public class StateMap {
    private State defaultState = new State("default");
    private final Map<Pair<State, String>, State> states;

    public StateMap() {
        this.states = new HashMap<>();
        State whitespaceState = new State("whitespace");
        State newLineState = new State("new line");

        states.put(new Pair<>(defaultState, "left brace"), newLineState);
        states.put(new Pair<>(defaultState, "right brace"), newLineState);
        states.put(new Pair<>(defaultState, "semicolon"), newLineState);
        states.put(new Pair<>(defaultState, "whitespace"), whitespaceState);
        states.put(new Pair<>(defaultState, "char"), defaultState);
        states.put(new Pair<>(defaultState, "string"), defaultState);
        states.put(new Pair<>(defaultState, "comma"), whitespaceState);
        states.put(new Pair<>(defaultState, "comment"), newLineState);

        states.put(new Pair<>(whitespaceState, "left brace"), newLineState);
        states.put(new Pair<>(whitespaceState, "right brace"), newLineState);
        states.put(new Pair<>(whitespaceState, "semicolon"), newLineState);
        states.put(new Pair<>(whitespaceState, "whitespace"), whitespaceState);
        states.put(new Pair<>(whitespaceState, "char"), defaultState);
        states.put(new Pair<>(whitespaceState, "string"), defaultState);
        states.put(new Pair<>(whitespaceState, "comma"), whitespaceState);
        states.put(new Pair<>(whitespaceState, "comment"), newLineState);

        states.put(new Pair<>(newLineState, "left brace"), newLineState);
        states.put(new Pair<>(newLineState, "right brace"), newLineState);
        states.put(new Pair<>(newLineState, "semicolon"), newLineState);
        states.put(new Pair<>(newLineState, "whitespace"), newLineState);
        states.put(new Pair<>(newLineState, "char"), defaultState);
        states.put(new Pair<>(newLineState, "string"), defaultState);
        states.put(new Pair<>(newLineState, "comma"), whitespaceState);
        states.put(new Pair<>(newLineState, "comment"), newLineState);
    }

    public State getStartState() {
        return defaultState;
    }

    public State getNextState(final State state, final String tokenName) {
        return states.getOrDefault(new Pair<>(state, tokenName), state);
    }

}
