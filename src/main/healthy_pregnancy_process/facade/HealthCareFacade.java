package main.healthy_pregnancy_process.facade;

import main.healthy_pregnancy_process.abstact_factory.AbstractMaternityHospitalFactory;
import main.healthy_pregnancy_process.singleton.MinistryOfHealth;
import main.healthy_pregnancy_process.abstact_factory.AbstractPrivateClinicFactory;
import main.healthy_pregnancy_process.abstact_factory.AbstractStateHospitalFactory;

/**
 * Создаем класс HealthCareFacade, который наследуется от класса MinistryOfHealth (представляющий паттерн проектирования
 * Singleton)
 */

public class HealthCareFacade extends MinistryOfHealth {
    private final AbstractStateHospitalFactory stateHospital;
    private final AbstractPrivateClinicFactory privateClinic;
    private final AbstractMaternityHospitalFactory maternityHospital;
// создаем конструктор класса HealthCareFacade, который принимает на вход следующие аргументы:
//                            StateHospital stateHospital,
//                            PrivateClinic privateClinic,
//                            MaternityHospital maternityHospital
    public HealthCareFacade(AbstractStateHospitalFactory stateHospital,
                            AbstractPrivateClinicFactory privateClinic,
                            AbstractMaternityHospitalFactory maternityHospital) {
        super("A ministry of health");

        this.stateHospital = stateHospital;
        this.privateClinic = privateClinic;
        this.maternityHospital = maternityHospital;
    }
    // Registration with the selected organization
    public void registerInStateHospital(){
        stateHospital.registerPatient();
    }
    public void registerInPrivateClinic(){
        privateClinic.registerPatient();
    }
    public void registerInMaternityHospital(){
        maternityHospital.registerPatient();
    }
}
