package main.healthy_pregnancy_process.service;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigObject;
import main.healthy_pregnancy_process.AppConfig;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Читает и парсит данные конфигурации из HOCON-файла и возвращает объект AppConfig
 * с заполненными данными о медицинских организациях, стратегиях выбора, состоянии девушки,
 * стратегиях девушки и месяцах беременности.
 *  Organizations - список медицинских организаций с их названиями
 *  hospital.selection.context.strategy - стратегия выбора медицинского учреждения
 *  информация о текущем состоянии беременной девушки
 *  girlStrategy - стратегические параметры поведения девушки
 *  pregnancy.months - данные о каждом месяце беременности
 *
 * filePath абсолютный или относительный путь к HOCON-файлу конфигурации
 * объект AppConfig с заполненными данными из конфигурационного файла
 *
 *      */

public class HoconReader {
    public static AppConfig readProjectDataFromFile(String filePath) {
            Config config = ConfigFactory.parseFile(new File(filePath));

            //чтение медицинских организаций из файла в формате AppConfig
            List<Map<String, Object>> organizations = config.getObjectList("organizations").stream()
                    .map(ConfigObject::unwrapped)
                    .toList();

        // Чтение стратегии выбора
        String selectionStrategy = config.getString("hospital.selection.context.strategy");

        // Чтение состояния девушки
        Map<String, Object> girlState = config.getObject("girlState").unwrapped();

        // Чтение стратегии девушки
        Map<String, Object> girlStrategy = config.getObject("girlStrategy").unwrapped();

            // Чтение данных о месяцах беременности
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
            }
            return new AppConfig(organizations, selectionStrategy, girlState, girlStrategy, pregnancyMonths);
    }
}


