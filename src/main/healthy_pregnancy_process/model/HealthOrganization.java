package main.healthy_pregnancy_process.model;

/* Abstract class representing a health organization */

import main.healthy_pregnancy_process.Healthy;
import main.healthy_pregnancy_process.facade.PatientRegistration;
import main.healthy_pregnancy_process.singleton.MVD;

public class HealthOrganization extends Healthy implements PatientRegistration {
    String name;

    public HealthOrganization(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "HealthOrganization{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * A method for registering a patient with a medical organization.
     */
    public void registerPatient() {

    }

    //отправка отчета о состоянии здоровья ребенка в MVD
    public void sendReport(String babyName, boolean isHealthy){
        MVD.getInstance().registerNewborn(babyName, isHealthy); // используем Singleton-экземпляр MVD
        if(!isHealthy){
            System.out.println(babyName + ": информация отправлена в Министерство внутренних дел");

        }
    }

}
