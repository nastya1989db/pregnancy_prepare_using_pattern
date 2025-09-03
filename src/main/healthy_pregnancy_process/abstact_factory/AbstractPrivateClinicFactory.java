package main.healthy_pregnancy_process.abstact_factory;

import main.healthy_pregnancy_process.model.HealthOrganization;

/**
 * класс, представляет частную клинику, реализующий паттерн проектирования Factory
 * A private clinic inheriting the HealthOrganization class.
 * Provides an opportunity to register patients.
 */
public class AbstractPrivateClinicFactory extends HealthOrganization {
    /**
     * Register a patient in a public hospital.
     */
    public AbstractPrivateClinicFactory() {
        super("A private clinic");
    }

    @Override
    public void registerPatient() {
        System.out.println("You are registered at a private clinic.");
    }
}
