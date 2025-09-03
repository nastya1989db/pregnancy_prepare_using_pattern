package main.healthy_pregnancy_process.abstact_factory;

import main.healthy_pregnancy_process.state.BabyState;

public class BabyAfterBirth implements BabyState {
    @Override
    public String describe() {
        return "Кушает, спит и развивается";
    }
}
