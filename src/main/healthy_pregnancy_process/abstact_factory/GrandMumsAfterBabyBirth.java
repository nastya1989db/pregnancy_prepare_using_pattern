package main.healthy_pregnancy_process.abstact_factory;

import main.healthy_pregnancy_process.state.GrandmumsState;

public class GrandMumsAfterBabyBirth implements GrandmumsState {
    @Override
    public String describe() {
        return "Помогают с ребенком молодой семье";
    }
}
