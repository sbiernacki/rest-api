package pl.biernat.week7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.biernat.week7.model.Car;
import pl.biernat.week7.service.CarService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/car")
public class CarApi {

    private CarService carService;

    @Autowired
    public CarApi(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getCars() {
        List<Car> cars = carService.getCars();
        if (cars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(carService.getCars(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Car> saveCar(@RequestBody Car car) {
        carService.saveCar(car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{carId}")
    public ResponseEntity deleteCar(@PathVariable Long carId){
        carService.deleteCar(carId);
        return new ResponseEntity<>("Pomyślne usunięto samochód o id = " + carId, HttpStatus.OK);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Car> findCarById(@PathVariable Long carId) {
        Car car = carService.findCarById(carId);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Car> updateCar(@RequestBody Car car) {
        carService.updateCar(car);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> findCarsByColor(@PathVariable String color) {
        List<Car> cars = carService.findCarsByColor(color);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/date/{dateFrom}/{dateTo}")
    public ResponseEntity<List<Car>> findCarsByProductionDate(@PathVariable String dateFrom, @PathVariable String dateTo) {
        List<Car> cars = carService.findCarsByProductionDate(dateFrom, dateTo);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
}

