package it.sevenbits.formatter.lexer.state_machine.state;

import it.sevenbits.formatter.lexer.state_machine.Pair;

import java.util.HashMap;
import java.util.Map;

public class StateMap {
    private State defaultState = new State("default");
    private State stringState = new State("string");
    private State commentState = new State("comment");

    private final Map<Pair<State, Character>, State> states;

    public StateMap() {
        this.states = new HashMap<>();
        State commentMBState = new State("commentMB");

        states.put(new Pair<>(defaultState, '"'), stringState);
        states.put(new Pair<>(stringState, '"'), defaultState);

        states.put(new Pair<>(defaultState, '/'), commentMBState);
        states.put(new Pair<>(commentMBState, '/'), commentState);
        states.put(new Pair<>(commentState, '\n'), defaultState);
    }

    public State getStartState() {
        return defaultState;
    }

    public State getNextState(final State state, final char nextChar) {
        if (state.equals(stringState) || state.equals(commentState)) {
            return states.getOrDefault(new Pair<>(state, nextChar), state);
        }
        return states.getOrDefault(new Pair<>(state, nextChar), defaultState);
    }

}
