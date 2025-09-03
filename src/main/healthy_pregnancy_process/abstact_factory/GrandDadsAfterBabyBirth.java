package main.healthy_pregnancy_process.abstact_factory;

import main.healthy_pregnancy_process.state.GranddadsState;

public class GrandDadsAfterBabyBirth implements GranddadsState {
    @Override
    public String describe() {
        return "Помогают с ребенком, с ремонтом и закупкой продуктов и стройматериалов";
    }
}
