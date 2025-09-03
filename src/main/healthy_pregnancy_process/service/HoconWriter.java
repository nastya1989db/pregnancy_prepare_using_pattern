package main.healthy_pregnancy_process.service;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValueFactory;
import main.healthy_pregnancy_process.model.HealthOrganization;
import main.healthy_pregnancy_process.model.VoterData;
import main.healthy_pregnancy_process.state.PregnancyMonth;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * Класс для записи данных проекта в файл формата HOCON.
 * Предоставляет функциональность для сериализации различных объектов
 * в конфигурационный файл с сохранением структуры данных.
 */
public class HoconWriter {
    /**
     * Записывает данные проекта в файл формата HOCON.
     * Сериализует информацию о медицинских организациях, состоянии беременной,
     * стратегиях выбора, данных голосования и информации о месяцах беременности.
     *
     * @param organizations список медицинских организаций для записи
     * @param girlStrategy карта с данными о стратегии беременной
     * @param girlState карта с данными о состоянии беременной
     * @param selectionStrategy название стратегии выбора медицинского учреждения
     * @param voters список данных о голосующих и их советах
     * @param pregnancyMonths список данных о месяцах беременности
     * @param filePath путь к файлу для записи данных
     * @throws IOException если возникает ошибка ввода-вывода при записи файла
     */
    public static void writeProjectDataToFile(
            List<HealthOrganization> organizations,
            Map<String, Object> girlStrategy,
            Map<String, Object> girlState,
            String selectionStrategy,
            List<VoterData> voters,
            List<PregnancyMonth> pregnancyMonths,
            String filePath
    ) throws IOException {
        // Подготовка данных об организациях
        List<Map<String, String>> organizationsList = organizations.stream()
                .map(org -> Map.of("name", org.getName()))
                .toList();
        // Подготовка данных о месяцах для записи
        List<Map<String, Object>> pregnancyMonthsList = new ArrayList<>();
            for(PregnancyMonth month: pregnancyMonths){
                Map<String, Object> monthData = new HashMap<>();
                monthData.put("number", month.getNumber());
                monthData.put("description", month.getDescription());
                monthData.put("advice", month.getAdvice());
                pregnancyMonthsList.add(monthData);
            }
        // Подготовка данных о voters
        List<Map<String, String>> votersList = voters.stream()
                .map(voter -> Map.of(
                        "type", voter.getType(),
                        "advice", voter.getAdvice()
                ))
                .toList();

        // Создаем конфигурацию
        Config config = ConfigFactory.empty() //создает пустой конфигурационный объект
                .withValue("organizations",
                        ConfigValueFactory.fromIterable(organizations.stream()
                                .map(org -> Map.of("name", org.getName()))
                                .collect(Collectors.toList())))
                // Добавляем в конфигурацию ключ "organizations", значением которого является список.
                // Для этого мы преобразуем список организаций (organizations) в список Map (каждая Map содержит ключ "name" и значение - имя организации).
                // Затем этот список преобразуется в ConfigValue с помощью ConfigValueFactory.fromIterable.
                .withValue("girlState", ConfigValueFactory.fromMap(girlState))
                // Добавляем ключ "girlState", значением является Map girlState,
                // преобразованная в ConfigValue.
                .withValue("girlStrategy", ConfigValueFactory.fromMap(girlStrategy))
                .withValue("hospital.selection.context.strategy", ConfigValueFactory.fromAnyRef(selectionStrategy))
                // Добавляем ключ с составным именем (путь)
                // и значением selectionStrategy (строка). Составное имя будет представлено вложенными объектами в HOCON.
                .withValue("voters",
                        ConfigValueFactory.fromIterable(voters.stream()
                                .map(v -> Map.of(
                                        "type", v.getType(),
                                        "advice", v.getAdvice()
                                ))
                                .collect(Collectors.toList())))
                // Добавляем ключ "voters", значением является список voters, преобразованный в список Map (каждая Map содержит
                // ключи "type" и "advice") и затем в ConfigValue.
                .withValue("pregnancy.months", ConfigValueFactory.fromIterable(pregnancyMonthsList));
        // Добавляем ключ "pregnancy.months", значением является список pregnancyMonthsList (каждый элемент этого списка
        // - это Map с ключами "number", "description", "advice").
        Files.write(Paths.get(filePath),
                config.root().render(ConfigRenderOptions
                        .defaults()
                        .setFormatted(true)
                        .setJson(false)
                ).getBytes()
                // В результате получается конфигурационный объект, который можно записать в файл в формате HOCON.
        );
    }
}
