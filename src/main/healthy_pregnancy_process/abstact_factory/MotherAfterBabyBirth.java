package main.healthy_pregnancy_process.abstact_factory;

import main.healthy_pregnancy_process.state.MotherState;

public class MotherAfterBabyBirth implements MotherState {

    @Override
    public String describe() {
        return "Занимается ребенком и восстанавливается после родов";
    }
}
