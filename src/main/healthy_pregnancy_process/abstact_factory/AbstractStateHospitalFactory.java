package main.healthy_pregnancy_process.abstact_factory;

import main.healthy_pregnancy_process.model.HealthOrganization;

/**
 * класс, представляет частные поликлиники, реализующие паттерн проектирования Factory
 * A public hospital inheriting the HealthOrganization class.
 * Provides an opportunity to register patients.
 */
public class AbstractStateHospitalFactory extends HealthOrganization {
    /**
     * Register a patient in a public hospital.
     */
    public AbstractStateHospitalFactory(){super("A state hospital");}
    @Override
    public void registerPatient() {
        System.out.println("Now you are registered at a public hospital.");
    }
}
