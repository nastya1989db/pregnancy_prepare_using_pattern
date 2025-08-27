package main.healthy_pregnancy_process.strategy;

import main.healthy_pregnancy_process.model.HealthOrganization;

import java.util.List;

/**
 * Интерфейс HospitalSelectionStrategy описывает общий принцип выбора медорганизации
 * и используется совместно разными частями системы, оптимальным решением будет создать отдельный пакет уровня верхнего слоя архитектуры для
 * хранения общих интерфейсов и базовых контрактов.
 */
public interface HospitalSelectionStrategy {
    HealthOrganization chooseHospital(List<HealthOrganization> organizations);
}
