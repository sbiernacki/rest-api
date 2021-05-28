package pl.biernat.week7.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Car {

    private Long carId;
    private String mark;
    private String model;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate productionDate;
    private String color;

    public Car(Long carId, String model, String mark, String color, LocalDate productionDate) {
        this.carId = carId;
        this.mark = mark;
        this.model = model;
        this.productionDate = productionDate;
        this.color = color;
    }

    public Car() {
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
