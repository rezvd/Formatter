package it.sevenbits.formatter.lexer.state_machine.state;

import java.util.Objects;

public class State {
    private String state;

    public State(final String state) {
        this.state = state;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        State state = (State) o;
        return Objects.equals(this.state, state.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.state);
    }

    @Override
    public String toString() {
        return this.state;
    }
}
