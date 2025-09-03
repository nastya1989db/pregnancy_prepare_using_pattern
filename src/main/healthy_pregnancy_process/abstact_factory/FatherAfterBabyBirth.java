package main.healthy_pregnancy_process.abstact_factory;

import main.healthy_pregnancy_process.state.FatherState;

public class FatherAfterBabyBirth implements FatherState {
    @Override
    public String describe() {
        return "Работает, обеспечивает семью материально и помогает с ребенком после работы";
    }
}
