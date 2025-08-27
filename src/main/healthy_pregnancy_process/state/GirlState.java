package main.healthy_pregnancy_process.state;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import main.healthy_pregnancy_process.model.Girl;
import main.healthy_pregnancy_process.model.HealthOrganization;
import main.healthy_pregnancy_process.singleton.MinistryOfHealth;
import main.healthy_pregnancy_process.strategy.*;
import main.healthy_pregnancy_process.visitor.Voting;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий состояние беременной девушки в различные месяцы беременности.
 * Расширяет базовый класс Girl, добавляя функциональность для управления состоянием
 * беременности, загрузки конфигурации месяцев и моделирования поведения во время беременности.
 *
 * Класс обеспечивает:
 * загрузку конфигурации месяцев беременности из HOCON-файла
 * определение поведения девушки в зависимости от текущего месяца беременности
 * принятие окончательного решения о выборе медицинского учреждения
 * взаимодействие с системой голосования для выбора стратегий поведения
 */
public class GirlState extends Girl {
    private int currentMonth;
    private Voting voting;
    private final List<PregnancyMonth> pregnancyMonths = new ArrayList<>();

    /**
     * Конструктор по умолчанию.
     * Инициализирует объект и загружает конфигурацию месяцев беременности.
     */
    public GirlState() {
        loadPregnancyMonthsConfig();
    }
    /**
     * Конструктор с передачей объекта голосования.
     * Инициализирует объект с указанной системой голосования
     * и загружает конфигурацию месяцев беременности.
     *
     * Voting система голосования для обработки стратегий поведения
     */
    public GirlState(Voting voting) {
        this.voting = voting;
        loadPregnancyMonthsConfig();
    }

    public GirlState(String name, Voting voting) {
        this.setName(name);
        this.voting = voting;
        this.context = new HospitalSelectionContext(null);
        loadPregnancyMonthsConfig();
    }
    /**
     * Возвращает текущий месяц беременности.
     *
     * @return номер текущего месяца беременности (от 1 до 9)
     */
    public int getCurrentMonth() {
        return currentMonth;
    }
    /**
     * Возвращает список данных о месяцах беременности, загруженных из конфигурации.
     *
     * @return список объектов PregnancyMonth с данными о каждом месяце беременности
     */
    public List<PregnancyMonth> getPregnancyMonths() {
        return pregnancyMonths;
    }
    /**
     * Устанавливает текущий месяц беременности.
     *
     * @param currentMonth номер месяца беременности (от 1 до 9)
     * @throws IllegalArgumentException если указан недопустимый номер месяца
     */
    public void setCurrentMonth(int currentMonth) {
        if (currentMonth < 1 || currentMonth > 9) {
            throw new IllegalArgumentException("Номер месяца беременности должен быть от 1 до 9");
        }
        this.currentMonth = currentMonth;
    }


    /**
     * Загружает конфигурацию месяцев беременности из HOCON-файла.
     * Читает данные из файла "src/main/resources/pregnancy_data.conf" и заполняет
     * список pregnancyMonths объектами PregnancyMonth с информацией о каждом месяце.

     * В случае ошибки загрузки выводит сообщение об ошибке в стандартный поток ошибок.
     */
    public void loadPregnancyMonthsConfig() {
        try {
         Config config = ConfigFactory.load("pregnancy_data.conf");
            if (config.hasPath("pregnancy.months")) {
                List<? extends Config> monthConfigs =
                        config.getConfigList("pregnancy.months");

                for (Config monthConfig : monthConfigs) {
                    PregnancyMonth month = new PregnancyMonth();
                    month.setNumber(monthConfig.getInt("number"));
                    month.setDescription(monthConfig.getString("description"));
                    month.setAdvice(monthConfig.getString("advice"));
                    pregnancyMonths.add(month);
                }
            } else {
                System.err.println("Конфигурация месяцев беременности не найдена");
            }
        } catch (Exception e) {
            System.err.println("Ошибка загрузки конфигурации: " + e.getMessage());
        }
    }

    /**
     * Получает данные о конкретном месяце беременности.
     * Сначала пытается найти данные в загруженной конфигурации, если данные не найдены,
     * использует значения по умолчанию для указанного месяца.
     *
     * @param month номер месяца беременности (от 1 до 9)
     * @return объект PregnancyMonth с данными о указанном месяце беременности
     * @throws IllegalArgumentException если указан недопустимый номер месяца
     */
    private PregnancyMonth getMonthData(int month) {
        if (month < 1 || month > 9) {
            throw new IllegalArgumentException("Номер месяца беременности должен быть от 1 до 9");
        }
        // Сначала ищем в загруженной конфигурации
        for (PregnancyMonth data : pregnancyMonths) {
            if (data.getNumber() == month) {
                return data;
            }
        }
        // Если данных в конфигурации нет, используем значения по умолчанию
        PregnancyMonth defaultMonth = new PregnancyMonth();
        defaultMonth.setNumber(month);

        switch (month) {
            case 1:
                defaultMonth.setDescription("Первый месяц - начало пути. Девушка узнает и радуется!");
                defaultMonth.setAdvice("FRIENDS_ADVICE");
                break;
            case 2:
                defaultMonth.setDescription("Второй месяц - первые изменения тела.");
                defaultMonth.setAdvice("DOCTOR_ADVICE");
                break;
            case 3:
                defaultMonth.setDescription("Третий месяц - токсикоз немного отпустил, настроение улучшилось - решила читать книги про материнство");
                defaultMonth.setAdvice("GRANDMA_ADVICE");
                break;
            case 4:
                defaultMonth.setDescription("Четвертый месяц - начинает чувствовать движения малыша внутри себя и еще усерднее следит за своим здоровьем");
                defaultMonth.setAdvice("SOCIAL_MEDIA");
                break;
            case 5:
                defaultMonth.setDescription("Пятый месяц - решилась купить курс на ютуб по материнству");
                defaultMonth.setAdvice("COURSES");
                break;
            case 6:
                defaultMonth.setDescription("Шестой месяц - пора задуматься о подготовке детской комнаты");
                defaultMonth.setAdvice("HUSBAND_FRIENDS_ADVICE");
                break;
            case 7:
                defaultMonth.setDescription("Седьмой месяц - интенсивная подготовка к родам, посещение курсов для будущих мам");
                defaultMonth.setAdvice("WORD_OF_MOUTH_ADVICE");
                break;
            case 8:
                defaultMonth.setDescription("Восьмой месяц - становится тяжелее двигаться, частые визиты к врачу");
                defaultMonth.setAdvice("HUSBAND_ADVICE");
                break;
            case 9:
                defaultMonth.setDescription("Девятый месяц - Полное сосредоточение на здоровье ребенка, еще более частые визиты к врачу");
                defaultMonth.setAdvice("DOCTOR_ADVICE");
                break;
            default:
                System.out.println("Ошибка: неверный месяц беременности");
                return null;
        }

        return defaultMonth;
    }
    /**
     * Моделирует поведение девушки в указанный месяц беременности.
     * Устанавливает текущий месяц, получает соответствующие данные о месяце,
     * выводит информацию о состоянии и обрабатывает голосование за соответствующую стратегию поведения.
     *
     * @param month номер месяца беременности (от 1 до 9)
     * @throws IllegalArgumentException если указан недопустимый номер месяца
     * @throws IllegalStateException если объект голосования не инициализирован
     */

    public void behaviourDuringPregnancy(int month) {
        if (month < 1 || month > 9) {
            throw new IllegalArgumentException("Номер месяца беременности должен быть от 1 до 9");
        }

        setCurrentMonth(month);
        PregnancyMonth monthData = getMonthData(month);
        if (monthData != null) {
            System.out.println("Девушка находится на " + month + " -ом месяце беременности: ");
            System.out.println(monthData.getDescription());
            // Используем совет из конфигурации
            GirlStrategy strategy = GirlStrategy.fromString(monthData.getAdvice());
            if (strategy != null) {
                if (voting != null) {
                    voting.processVote(strategy);
                } else {
                    throw new IllegalStateException("Ошибка: объект голосования не инициализирован");
                }
                } else {
                    System.out.println("Неизвестный тип совета: " + monthData.getAdvice());
                }
            } else {
                System.out.println("Ошибка: неверный месяц беременности");
            }

        }
    /**
     * Принимает окончательное решение о выборе медицинского учреждения.
     * Получает список всех зарегистрированных организаций, устанавливает стратегию
     * выбора единственного учреждения и осуществляет выбор оптимального медицинского заведения.
     * Использует паттерн Strategy через HospitalSelectionContext для выбора учреждения.

     */

    public void makeFinalChoice() {
        List<HealthOrganization> allOrganizations = MinistryOfHealth.getInstance().getAllOrganizations();
        // Устанавливаем стратегию выбора единственного учреждения
        context.setSelectionStrategy(new SingleHospitalSelectionStrategy());
        // Осуществляем выбор одного оптимального заведения
        HealthOrganization chosenHospital = context.selectHospital(allOrganizations);
    }
}

