package pl.biernat.week7.dao.impl;

import pl.biernat.week7.model.Car;

import java.util.List;

public interface CarDao {

    List<Car> findAllCars();

    void saveCar(Car car);

    void deleteCar(Long carId);

    Car findCarById(Long carId);

    void update(Car car);

    List<Car> findCarsByColor(String color);

    List<Car> findCarsByProductionDate(String dateFrom, String dateTo);
}
