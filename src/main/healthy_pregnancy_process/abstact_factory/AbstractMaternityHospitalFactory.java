package main.healthy_pregnancy_process.abstact_factory;

import main.healthy_pregnancy_process.model.HealthOrganization;

/**
 * класс, представляет роддома, реализующий паттерн проектирования Factory
 * A maternity hospital inheriting the HealthOrganization class.
 * Provides an opportunity to register patients.
 */
public class AbstractMaternityHospitalFactory extends HealthOrganization {

    /**
     * Register a patient in a public hospital.
     */
    public AbstractMaternityHospitalFactory() { super("A maternity hospital"); }
    @Override
    public void registerPatient() {
        System.out.println("You are registered at a maternity hospital");

    }
}
