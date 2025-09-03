package main.healthy_pregnancy_process.strategy;

import main.healthy_pregnancy_process.abstact_factory.AbstractPrivateClinicFactory;
import main.healthy_pregnancy_process.model.HealthOrganization;

import java.util.List;

public class PrivateClinicSelectionStrategy implements HospitalSelectionStrategy {
    @Override
    public HealthOrganization chooseHospital(List<HealthOrganization> organizations) {
        for (HealthOrganization org : organizations){
            if (org instanceof AbstractPrivateClinicFactory) {
                return org;
            }
        }
       throw new RuntimeException("Не нашлось подходящей платной клиники");
    }
}
