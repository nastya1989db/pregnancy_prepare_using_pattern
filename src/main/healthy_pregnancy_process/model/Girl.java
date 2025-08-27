package main.healthy_pregnancy_process.model;

import main.healthy_pregnancy_process.strategy.HospitalSelectionContext;

public class Girl {
    private String name = "Nasty";
    protected HospitalSelectionContext context;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
