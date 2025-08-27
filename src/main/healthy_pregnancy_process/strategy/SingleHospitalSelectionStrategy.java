package main.healthy_pregnancy_process.strategy;

import main.healthy_pregnancy_process.model.HealthOrganization;

import java.util.List;

public class SingleHospitalSelectionStrategy implements HospitalSelectionStrategy{
    @Override
    public HealthOrganization chooseHospital(List<HealthOrganization> organizations) {
        for (HealthOrganization org : organizations){
            if(org.getName().contains("Мерси")){
                return org;
            }
        }
       throw new RuntimeException("Нет подходящего центра для регистрации");
    }
}
