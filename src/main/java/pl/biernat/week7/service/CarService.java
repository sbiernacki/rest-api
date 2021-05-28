package pl.biernat.week7.service;

import pl.biernat.week7.model.Car;

import java.util.List;

public interface CarService {

    List<Car> getCars();

    void saveCar(Car car);

    void deleteCar(Long carId);

    Car findCarById(Long carId);

    void updateCar(Car car);

    List<Car> findCarsByColor(String color);

    List<Car> findCarsByProductionDate(String dateFrom, String dateTo);
}
