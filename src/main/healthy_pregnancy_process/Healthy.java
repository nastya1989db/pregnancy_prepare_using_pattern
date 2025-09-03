package main.healthy_pregnancy_process;

import main.healthy_pregnancy_process.model.Baby;

public class Healthy extends Baby {
    public Healthy() {
    }

    public Healthy(String childName, boolean isHealthy) {
        // Вызов конструктора родительского класса с корректными параметрами
        super(childName, isHealthy);
    }

}


