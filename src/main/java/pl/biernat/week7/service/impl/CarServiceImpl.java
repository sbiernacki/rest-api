package pl.biernat.week7.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.biernat.week7.dao.impl.CarDao;
import pl.biernat.week7.model.Car;
import pl.biernat.week7.service.CarService;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarDao carDao;

    @Autowired
    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public List<Car> getCars() {
        return carDao.findAllCars();
    }

    @Override
    public void saveCar(Car car) {
        carDao.saveCar(car);
    }

    @Override
    public void deleteCar(Long carId) {
        carDao.deleteCar(carId);
    }

    @Override
    public Car findCarById(Long carId) {
        return carDao.findCarById(carId);
    }

    @Override
    public void updateCar(Car car) {
        carDao.update(car);
    }

    @Override
    public List<Car> findCarsByColor(String color) {
        return carDao.findCarsByColor(color);
    }

    @Override
    public List<Car> findCarsByProductionDate(String dateFrom, String dateTo) {
        return carDao.findCarsByProductionDate(dateFrom, dateTo);
    }
}
