package it.sevenbits.formatter.formatter.state_machine.state;


import it.sevenbits.formatter.token.IToken;
public class StateTransitions {
    private final StateMap stateMap;

    public StateTransitions() {
        this.stateMap = new StateMap();
    }

    public State getStartState() {
        return stateMap.getStartState();
    }

    public State getNextState(final State state, final String tokenName) {
        return stateMap.getNextState(state, tokenName);
    }
}
