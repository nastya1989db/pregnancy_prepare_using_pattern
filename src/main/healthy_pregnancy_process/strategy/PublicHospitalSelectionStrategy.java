package main.healthy_pregnancy_process.strategy;

import main.healthy_pregnancy_process.abstact_factory.AbstractStateHospitalFactory;
import main.healthy_pregnancy_process.model.HealthOrganization;

import java.util.List;

public class PublicHospitalSelectionStrategy implements HospitalSelectionStrategy{

    @Override
    public HealthOrganization chooseHospital(List<HealthOrganization> organizations) {
        for (HealthOrganization org : organizations){
            if(org instanceof AbstractStateHospitalFactory){
                return org;
            }
        } throw new RuntimeException("Подходящей государственной поликлинике не нашлось.");
    }
}
