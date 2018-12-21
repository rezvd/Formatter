package it.sevenbits.formatter.lexer.state_machine.state;

public class StateTransitions {
    private final StateMap stateMap;

    public StateTransitions() {
        this.stateMap = new StateMap();
    }

    public State getStartState() {
        return stateMap.getStartState();
    }

    public State getNextState(final State state, final char nextChar) {
        return stateMap.getNextState(state, nextChar);

    }
}
