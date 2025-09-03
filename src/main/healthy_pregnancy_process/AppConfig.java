package main.healthy_pregnancy_process;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Класс AppConfig (хранит конфигурационные данные) будет представлять нужные узлы конфигурации
 * и соответствующие им поля
 * - Список зарегистрированных организаций здравоохранения (organizations)
 * - Стратегия выбора медицинской организации (selection_strategy)
 * - Информация о девушке, ведущей беременность (girl_state)
 * - Перечисление возможных стратегий получения советов и поддержки женщине
 * во время беременности (girl_strategy)
 */
public class AppConfig {
    @JsonProperty("organizations")
    private List<Map<String, Object>> organizations;
    @JsonProperty("selectionStrategy")
    private String selectionStrategy;
    @JsonProperty("girlState")
    private Map<String, Object> girlState;
    @JsonProperty("girlStrategy")
    private Map<String, Object> girlStrategy;
    @JsonProperty("pregnancyMonths")
    private Map<Integer, Map<String, String>> pregnancyMonths;

    // Создаем конструктор

    public AppConfig() {
    }

    public AppConfig(List<Map<String, Object>> organizations,
                     String selectionStrategy, Map<String, Object> girlState,
                     Map<String, Object> girlStrategy,
                     Map<Integer, Map<String, String>> pregnancyMonths) {
        this.organizations = organizations;
        this.selectionStrategy = selectionStrategy;
        this.girlState = girlState;
        this.girlStrategy = girlStrategy;
        this.pregnancyMonths = pregnancyMonths;
    }
    // Геттеры и сеттеры

    public List<Map<String, Object>> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Map<String, Object>> organizations) {
        this.organizations = organizations;
    }

    public String getSelectionStrategy() {
        return selectionStrategy;
    }

    public void setSelectionStrategy(String selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }

    public Map<String, Object> getGirlState() {
        return girlState;
    }

    public void setGirlState(Map<String, Object> girlState) {
        this.girlState = girlState;
    }

    public Map<String, Object> getGirlStrategy() {

        return girlStrategy;
    }

    public void setGirlStrategy(Map<String, Object> girlStrategy) {
        this.girlStrategy = girlStrategy;
    }

    public Map<Integer, Map<String, String>> getPregnancyMonths() {
        return pregnancyMonths;
    }

    public void setPregnancyMonths(Map<Integer, Map<String, String>> pregnancyMonths) {
        this.pregnancyMonths = pregnancyMonths;
    }


}
