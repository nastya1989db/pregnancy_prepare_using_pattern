package main.healthy_pregnancy_process.strategy;

import main.healthy_pregnancy_process.model.HealthOrganization;

import java.util.List;

/**
 * Класс HospitalSelectionContext относится непосредственно к механизму реализации паттерна Strategy,
 * поскольку он управляет сменой стратегий выбора медицинских учреждений и
 * служит контекстом для выбора нужной стратегии.
 * Контекст будет использоваться девушкой для смены стратегии
 * и выбора подходящего учреждения
 */
public class HospitalSelectionContext {
    private HospitalSelectionStrategy selectionStrategy;

    public HospitalSelectionContext(HospitalSelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }

    public HospitalSelectionStrategy getSelectionStrategy() {
        return selectionStrategy;
    }

    public void setSelectionStrategy(HospitalSelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }
    public HealthOrganization selectHospital (List<HealthOrganization> organizations){
        return selectionStrategy.chooseHospital(organizations);
    }
}
