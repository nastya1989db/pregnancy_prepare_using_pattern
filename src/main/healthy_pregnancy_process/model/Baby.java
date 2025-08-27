package main.healthy_pregnancy_process.model;

public class Baby {
    private String babyName = "Alice";
    private boolean isHealthy;

    public Baby() {
    }

    public Baby(String babyName, boolean isHealthy){
        this.babyName = babyName;
        this.isHealthy = isHealthy;
    }

    public String getChildName() {
        return babyName;
    }

    public void setChildName(String babyName) {
        this.babyName = babyName;
    }
}
