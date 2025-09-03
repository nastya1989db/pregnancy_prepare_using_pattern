package main.healthy_pregnancy_process.strategy;

public enum GirlStrategy {
    /**
     * Перечисление возможных стратегий получения советов и поддержки женщине во время беременности
     */
    COURSES("Посещение курсов для будущих мам"), //специальные курсы для беременных
    GRANDMA_ADVICE("Советы и помощь бабушки"), //поддержка и советы бабушки
    FRIENDS_ADVICE("Советы и помощь подруг"), //поддержка и советы подруг
    SOCIAL_MEDIA("Соцсети"), //информация из социальных сетей
    WORD_OF_MOUTH_ADVICE("Сарафанное радио"), //мнения знакомых и соседей
    HUSBAND_ADVICE("Совет мужа"), //совет супруга
    HUSBAND_FRIENDS_ADVICE("Советы друзей мужа"), //дружеская помощь от окружения мужа
    DOCTOR_ADVICE("Советы доктора");//советы доктора
    private final String description;

    GirlStrategy(String description) {
        this.description = description;
    }

    /**
     * Возвращает подробное описание выбранной стратегии
     *
     * @return строковое представление стратегии
     */
    public String getDescription() {
        return description;
    }

    /**
     * добавляем вспомогательный метод
     * преобразования строки в элемент перечисления
     *
     * @param value
     * @return
     */
    public static GirlStrategy fromString(String value) {
        for (GirlStrategy strategy : values()) {
            if (value.contains(strategy.getDescription()) || strategy.name().equalsIgnoreCase(value)) {
                return strategy;
            }
        }
        throw new IllegalArgumentException("Invalid girlStrategy: " + value);
    }
    @Override
    public String toString() {
        return description;
    }
}

