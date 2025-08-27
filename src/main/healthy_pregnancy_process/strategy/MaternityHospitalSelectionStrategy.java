package main.healthy_pregnancy_process.strategy;

import main.healthy_pregnancy_process.abstact_factory.AbstractMaternityHospitalFactory;
import main.healthy_pregnancy_process.model.HealthOrganization;

import java.util.List;

public class MaternityHospitalSelectionStrategy implements HospitalSelectionStrategy{
    @Override
    public HealthOrganization chooseHospital(List<HealthOrganization> organizations) {
        for (HealthOrganization org : organizations){
            if (org instanceof AbstractMaternityHospitalFactory){
                return org;
            }
        }
       throw new RuntimeException("Подходящего роддома не найдено");
    }
}
