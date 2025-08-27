package main.healthy_pregnancy_process.facade;

import main.healthy_pregnancy_process.state.*;

public class FamilyWithBabyFacade {
    private MotherState motherState;
    private FatherState fatherState;
    private BabyState babyState;
    private GrandmumsState grandmumsState;
    private GranddadsState granddadsState;

    public FamilyWithBabyFacade(MotherState motherState, FatherState fatherState,
                                BabyState babyState, GrandmumsState grandmumsState,
                                GranddadsState granddadsState) {
        this.motherState = motherState;
        this.fatherState = fatherState;
        this.babyState = babyState;
        this.grandmumsState = grandmumsState;
        this.granddadsState = granddadsState;
    }

    public void changeMotherState(MotherState state) {
        this.motherState = state;
    }

    public void changeBabyState(BabyState state) {
        this.babyState = state;
    }

    public void changeFatherState(FatherState state) {
        this.fatherState = state;
    }

    public void changeGrandmumsState(GrandmumsState state) {
        this.grandmumsState = state;
    }

    public void changeGranddadsState(GranddadsState state) {
        this.granddadsState = state;
    }

    public void reportStatus() {
        System.out.println("Семья:");
        System.out.println("- Мама: " + motherState.describe());
        System.out.println("- Ребёнок: " + babyState.describe());
        System.out.println("- Папа: " + fatherState.describe());
        System.out.println("- Бабушки: " + grandmumsState.describe());
        System.out.println("- Дедушки: " + granddadsState.describe());
    }
}


