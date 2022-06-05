package com.kzhou.wordcount;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/5/8 22:15
 **/
public class SensorReading {
    private String sensorId;
    private Long number;
    private Double number2;

    public SensorReading() {
    }

    public SensorReading(String sensorId, Long number, Double number2) {
        this.sensorId = sensorId;
        this.number = number;
        this.number2 = number2;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Double getNumber2() {
        return number2;
    }

    public void setNumber2(Double number2) {
        this.number2 = number2;
    }

    @Override
    public String toString() {
        return "SensorReading{" +
                "sensorId='" + sensorId + '\'' +
                ", number=" + number +
                ", number2=" + number2 +
                '}';
    }
}
