package main.healthy_pregnancy_process.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Описаем наши данные (голосующие, их советы и типы) в виде моделей,
 * совместимых с сериализацией в JSON.
 */
public class VoterData {
    @JsonProperty("type")
    private String type;// Тип голосующего
    @JsonProperty("advice")
    private String advice; //Совет, предоставляемый голосующим

    public VoterData() {
    }

    public VoterData(@JsonProperty("type") String type, @JsonProperty("advice") String advice){
        this.type = type;
        this.advice = advice;
    }

    public String getType() {
        return type;
    }

    public String getAdvice() {
        return advice;
    }
}
