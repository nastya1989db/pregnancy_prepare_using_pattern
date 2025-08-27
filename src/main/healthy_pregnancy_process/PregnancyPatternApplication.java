package main.healthy_pregnancy_process;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import main.healthy_pregnancy_process.abstact_factory.*;
import main.healthy_pregnancy_process.facade.FamilyWithBabyFacade;
import main.healthy_pregnancy_process.service.HoconReader;
import main.healthy_pregnancy_process.service.HoconWriter;
import main.healthy_pregnancy_process.model.HealthOrganization;
import main.healthy_pregnancy_process.model.Voter;
import main.healthy_pregnancy_process.model.VoterData;
import main.healthy_pregnancy_process.singleton.MinistryOfHealth;
import main.healthy_pregnancy_process.state.GirlState;
import main.healthy_pregnancy_process.state.PregnancyMonth;
import main.healthy_pregnancy_process.strategy.GirlStrategy;
import main.healthy_pregnancy_process.strategy.HospitalSelectionContext;
import main.healthy_pregnancy_process.strategy.SingleHospitalSelectionStrategy;
import main.healthy_pregnancy_process.visitor.*;

import java.io.IOException;
import java.util.*;

/**
 * Главный класс приложения, моделирующий процесс сопровождения беременности.
 * Использует различные паттерны проектирования для выбора медицинского учреждения,
 * управления состояниями беременности и обработки голосов советов.
 */

public class PregnancyPatternApplication {
    /**
     * Точка входа в приложение. Инициализирует компоненты системы,
     * регистрирует медицинские учреждения, обрабатывает конфигурацию
     * и имитирует процесс принятия решений during беременности.
     *
     * @throws IllegalAccessException если возникает ошибка доступа
     */
    public static void main(String[] args) throws IllegalAccessException {
        // Получаем экземпляр Министерства здравоохранения
        MinistryOfHealth ministry = MinistryOfHealth.getInstance();
        // создаем экземпляры класса: а именно частные клиники, государственные поликлиники и роддома, где будущая мама
        //планирует зарегистрироваться, обслуживаться во время беременности и рожать;
        AbstractPrivateClinicFactory privateClinic = new AbstractPrivateClinicFactory();
        AbstractStateHospitalFactory stateHospital = new AbstractStateHospitalFactory();
        AbstractMaternityHospitalFactory maternityHospital = new AbstractMaternityHospitalFactory();

        // Создаем, регистрируем и просматриваем зарегистрированные организации в Министерстве Здравоохранения
        ministry.registerOrganization(new HealthOrganization("Городская поликлиника 13"));
        ministry.registerOrganization(new HealthOrganization("Городская поликлиника 34"));
        ministry.registerOrganization(new HealthOrganization("Городская поликлиника 25"));
        ministry.registerOrganization(new HealthOrganization("Городская поликлиника 17"));
        ministry.registerOrganization(new HealthOrganization("Городская поликлиника 36"));
        ministry.registerOrganization(new HealthOrganization("Роддом №1"));
        ministry.registerOrganization(new HealthOrganization("Роддом №2"));
        ministry.registerOrganization(new HealthOrganization("Роддом №3"));
        ministry.registerOrganization(new HealthOrganization("Роддом №4"));
        ministry.registerOrganization(new HealthOrganization("ЧМЦ Мерси"));
        ministry.registerOrganization(new HealthOrganization("ЧМЦ ЛОДЭ"));
        ministry.registerOrganization(new HealthOrganization("ЧМЦ Конфидент"));
        ministry.registerOrganization(new HealthOrganization("ЧМЦ Эксанамед"));
        ministry.registerOrganization(new HealthOrganization("ЧМЦ Акваклиник"));


        // загрузка конфигурации
        Config config = ConfigFactory.load("pregnancy_data.conf");
        AppConfig appConfig = ConfigLoader.loadAppConfig(config);
        // Регистрация организаций из конфигурации
        appConfig.getOrganizations().forEach(orgMap -> {
            String name = (String) orgMap.get("name");
            ministry.registerOrganization(new HealthOrganization(name));
        });

        // Печатаем список организаций ТОЛЬКО ОДИН РАЗ после регистрации
        ministry.printRegisteredOrganizations();
        //Создаем голосование
        Voting voting = new Voting();
        // Объект Girl представляет нашу героиню Nasty
        GirlState girlState = new GirlState("Nasty", voting);
        // Контекст выбора, где беременная девушка регистрируется для получения медицинских услуг
        // и обслуживания всего цикла беременности и родов
        HospitalSelectionContext context = new HospitalSelectionContext(null);
        context.setSelectionStrategy(new SingleHospitalSelectionStrategy());
        // Получаем список организаций БЕЗ ПОДТВЕРЖДЕНИЯ НА КОНСОЛИ
        List<HealthOrganization> organizations = ministry.getAllOrganizations();
        PregnancyMonth pregnancyMonth = new PregnancyMonth();
        girlState.behaviourDuringPregnancy(5);
        // Беременной девушке предстоит выбрать одно лучшее медицинское учреждение
        HealthOrganization bestHospital = context.selectHospital(MinistryOfHealth.getInstance().getAllOrganizations());
        girlState.makeFinalChoice();
        // Финальный выбор медучреждения, где беременная девушка будет обслуживаться
        System.out.println(girlState.getName() + " окончательно выбрала организацию для сопровождения беременности: " + bestHospital.getName());

        //регистрация здорового ребенка
        bestHospital.sendReport("Alice", true);

        // Создаем единую семью
        FamilyWithBabyFacade myFamily = new FamilyWithBabyFacade(
                new MotherAfterBabyBirth(),    // мама занимается ребенком и восстанавливает свой организм после родов
                new FatherAfterBabyBirth(),   // работает, обеспечивает семью материально и помогает с ребенком после работы
                new BabyAfterBirth(),         // ребенок спит, кушает и развивается
                new GrandMumsAfterBabyBirth(),   // бабушка помогает с ребенком
                new GrandDadsAfterBabyBirth()    // дедушка помогает с ребенком, с ремонтом и закупкой продуктов и стройматериалов
        );

        // Выводим статус семьи
        myFamily.reportStatus();

// dataList список данных о голосующих и их советах
        List<VoterData> dataList = Arrays.asList(
                new VoterData("doctor", "COURSES"),
                new VoterData("grandma", "GRANDMA_ADVICE"),
                new VoterData("friend", "FRIENDS_ADVICE"),
                new VoterData("social media", "SOCIAL_MEDIA"),
                new VoterData("word of mouth", "WORD_OF_MOUTH_ADVICE"),
                new VoterData("husband advice", "HUSBAND_ADVICE"),
                new VoterData("husband friends", "HUSBAND_FRIENDS_ADVICE")
        );

        // Создаем данные о месяцах беременности по умолчанию
        List<PregnancyMonth> defaultMonths = List.of(
                new PregnancyMonth(1, "Первый месяц - начало пути. Девушка узнает и радуется!", "FRIENDS_ADVICE"),
                new PregnancyMonth(2, "Второй месяц - первые изменения тела.", "DOCTOR_ADVICE"),
                new PregnancyMonth(3, "Третий месяц - токсикоз немного отпустил, настроение улучшилось - решила читать книги про материнство", "GRANDMA_ADVICE"),
                new PregnancyMonth(4, "Четвертый месяц - начинает чувствовать движения малыша внутри себя и еще усерднее следит за своим здоровьем", "SOCIAL_MEDIA"),
                new PregnancyMonth(5, "Пятый месяц - решилась купить курс на ютуб по материнству", "COURSES"),
                new PregnancyMonth(6, "Шестой месяц - пора задуматься о подготовке детской комнаты", "HUSBAND_FRIENDS_ADVICE"),
                new PregnancyMonth(7, "Седьмой месяц - интенсивная подготовка к родам, посещение курсов для будущих мам", "WORD_OF_MOUTH_ADVICE"),
                new PregnancyMonth(8, "Восьмой месяц - становится тяжелее двигаться, частые визиты к врачу", "HUSBAND_ADVICE"),
                new PregnancyMonth(9, "Девятый месяц - Полное сосредоточение на здоровье ребенка, еще более частые визиты к врачу", "DOCTOR_ADVICE")
        );


        try {
            // Запись данных
            HoconWriter.writeProjectDataToFile(
                    ministry.getAllOrganizations(),
                    appConfig.getGirlStrategy(),
                    appConfig.getGirlState(),
                    context.getSelectionStrategy().getClass().getSimpleName(),
                    dataList,
                    defaultMonths, // Передаем заполненные данные о месяцах
                    "pregnancy_data.conf"
            );

            // Чтение данных
            AppConfig restored = HoconReader.readProjectDataFromFile("pregnancy_data.conf");
            System.out.println("Восстановленные организации: " + restored.getOrganizations());

            // создание нового списка избирателей путём передачи некоторых данных в метод createVoters().
            List<Voter> voters = createVoters(dataList);
            for (Voter voter : voters) {
                voter.accept(voting);
            }
            GirlStrategy winner = voting.getWinner();
            System.out.println("Победившая стратегия: " + winner.toString());

        } catch (IOException exception) {
            System.err.println("Ошибка при чтении файла: " + exception.getMessage());
            exception.printStackTrace();
        }


    }

    /**
     * Создает список голосующих на основе предоставленных данных.
     *
     * @param dataList список данных о голосующих и их советах
     * @return список инициализированных объектов {@link Voter}
     * @throws RuntimeException если встречается неизвестный тип советчика
     */
    private static List<Voter> createVoters(List<VoterData> dataList) {
        List<Voter> voters = new ArrayList<>();
        for (VoterData vData : dataList) {
            switch (vData.getType().toLowerCase()) {
                case "doctor":
                    voters.add(new VoterDoctor(GirlStrategy.fromString(vData.getAdvice())));
                    break;
                case "grandma":
                    voters.add(new VoterGrandma(GirlStrategy.fromString(vData.getAdvice())));
                    break;
                case "friend":
                    voters.add(new VoterFriend(GirlStrategy.fromString(vData.getAdvice())));
                    break;
                case "social media":
                    voters.add(new VoterSocialMedia(GirlStrategy.fromString(vData.getAdvice())));
                    break;
                case "word of mouth":
                    voters.add(new VoterWordOfMouth(GirlStrategy.fromString(vData.getAdvice())));
                    break;
                case "husband advice":
                    voters.add(new VoterHusbandAdvice(GirlStrategy.fromString(vData.getAdvice())));
                    break;
                case "husband friends":
                    voters.add(new VoterHusbandFriends(GirlStrategy.fromString(vData.getAdvice())));
                    break;
                default:
                    throw new RuntimeException("Unknown voter type: " + vData.getAdvice());
            }
        }
        return voters;
    }

}