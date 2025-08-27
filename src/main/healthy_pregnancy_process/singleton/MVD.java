package main.healthy_pregnancy_process.singleton;

public class MVD {
    //приватный статический экземпляр класса
    private static final MVD instance = new MVD();
    //конструктор закрыт для предотвращения прямого инстанцирования
    private MVD() {
    }
    //глобальная точка доступа для получения единственного экземпляра
    public static MVD getInstance() {
        return instance;
    }
    //метод регистрации новорожденного ребенка
    public void registerNewborn(String babyName, boolean isHealthy){
        if (isHealthy) {
            System.out.println("Ребенок здоров, выписан домой и успешно зарегистрирован!");
        } else {
            transferToOtherHospital(babyName);
        }
    }
    private void transferToOtherHospital(String babyName){
        System.out.println("Ребенок отправлен в другой роддом для дальнейшего обследования!");
    }
}
