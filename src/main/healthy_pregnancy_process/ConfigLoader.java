package main.healthy_pregnancy_process;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// класс ConfigLoader - утилитный класс для загрузки конфигурации

public class ConfigLoader {
    public static AppConfig loadAppConfig(Config config) {

        // загрузка всех зарегистрированных организаций
        List<Map<String, Object>> organizations = List.of(); //по умолчанию пустой список
        if (config.hasPath("organizations")) {
            organizations = config.getObjectList("organizations").stream()
                    .map(ConfigObject::unwrapped)
                    .toList();
        }
        //загрузка стратегий (по выбору медицинского учреждения)
        String selectionStrategy = "single"; // значение по умолчанию
        if (config.hasPath("hospital.selection.context.strategy")) {
            selectionStrategy = config.getString("hospital.selection.context.strategy");
        }

        // загрузка информации о состоянии девушки по месяцам (1 - 9)
        Map<String, Object> girlState = new HashMap<>();
        if (config.hasPath("girlState")) {
            girlState = config.getObject("girlState").unwrapped();
        } else {
            // Значения по умолчанию
            girlState.put("name", "Nasty");
            girlState.put("age", 26);
            girlState.put("pregnancyWeek", "24");
        }
        // загрузка стратегии девушки по выбору советов
        Map<String, Object> girlStrategy = new HashMap<>();
        if (config.hasPath("girlStrategy")) {
            girlStrategy = config.getObject("girlStrategy").unwrapped();
        } else {
            girlStrategy.put("primaryStrategy", "DOCTOR_ADVICE");
            girlStrategy.put("finalStrategy", "FRIENDS_ADVICE");
        }

        // Загрузка данных о месяцах беременности
        Map<Integer, Map<String, String>> pregnancyMonths = new HashMap<>();
        if (config.hasPath("pregnancy.months")) {
            List<? extends ConfigObject> monthConfigs = config.getObjectList("pregnancy.months");
            for (ConfigObject monthObj : monthConfigs) {
                Map<String, Object> monthMap = monthObj.unwrapped();
                int monthNumber = (Integer) monthMap.get("number");
                Map<String, String> monthData = new HashMap<>();
                // Динамическое извлечение всех строковых полей
                monthMap.forEach((key, value) -> {
                    if (!"number".equals(key) && value instanceof String) {
                        monthData.put(key, (String) value);
                    }
                });

                pregnancyMonths.put(monthNumber, monthData);
            }
        } else {
            // Значения по умолчанию для месяцев беременности
            pregnancyMonths.put(1, Map.of(
                    "description", "Первый месяц - начало пути. Девушка узнает и радуется!",
                    "advice", "FRIENDS_ADVICE"
            ));
            pregnancyMonths.put(2, Map.of(
                    "description", "Второй месяц - первые изменения тела.",
                    "advice", "DOCTOR_ADVICE"
            ));
            // Добавьте остальные месяцы по аналогии
        }
        return new AppConfig(organizations, selectionStrategy, girlState, girlStrategy, pregnancyMonths);
    }
}

