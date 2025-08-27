package main.healthy_pregnancy_process.singleton;

import main.healthy_pregnancy_process.model.HealthOrganization;

import java.util.ArrayList;
import java.util.List;

/**
 * Простое представление Министерства здравоохранения Беларуси.
 * Использует шаблон Singleton для уникального экземпляра и сохраняет список организаций.
 */
public class MinistryOfHealth {

    /* Единственный экземпляр Министерства */
    private static MinistryOfHealth instance = null;

    /* Список связанных организаций здравоохранения */
    private final List<HealthOrganization> organizations;

    /**
     * Приватный конструктор, чтобы запретить прямую инициализацию вне класса.
     */
    protected MinistryOfHealth(String aMinistryOfHealth) {
        organizations = new ArrayList<>();
    }

    /**
     * Получить единственный экземпляр Министерства здравоохранения.
     *
     * @return Экземпляр Министерства здравоохранения.
     */
    public static synchronized MinistryOfHealth getInstance() {
        if (instance == null) {
            instance = new MinistryOfHealth("A ministry of health");
        }
        return instance;
    }

    /**
     * Зарегистрируйте новую организацию здравоохранения в реестре Министерства.
     *
     * @param organization Новую организацию здравоохранения.
     */
    public void registerOrganization(HealthOrganization organization) {
        if (organization != null)
            organizations.add(organization);
    }
    public void printRegisteredOrganizations() {
        System.out.println("Зарегистрированные организации здравоохранения:");
        for (HealthOrganization org : organizations) {
            System.out.println("- " + org.getName());
        }
    }
    /**
     * Вывести список всех зарегистрированных организаций здравоохранения.
     *
     * @return
     */
    public List<HealthOrganization> getAllOrganizations() {
        return organizations;
    }
}
