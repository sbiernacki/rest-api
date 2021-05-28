package pl.biernat.week7.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.biernat.week7.dao.impl.CarDao;
import pl.biernat.week7.model.Car;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CarDaoImpl implements CarDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Car> findAllCars() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
        results.forEach(element -> cars.add(new Car(
                Long.parseLong(String.valueOf(element.get("car_id"))),
                String.valueOf(element.get("model")),
                String.valueOf(element.get("mark")),
                String.valueOf(element.get("color")),
                LocalDate.parse(String.valueOf(element.get("production_date")))
        )));
        return cars;
    }

    @Override
    public void saveCar(Car car) {
        String sql = "INSERT INTO cars VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, car.getCarId(), car.getMark(), car.getModel(), car.getColor(), car.getProductionDate());
    }

    @Override
    public void deleteCar(Long carId) {
        String sql = "DELETE FROM cars WHERE car_id = ?";
        jdbcTemplate.update(sql, carId);
    }

    @Override
    public Car findCarById(Long carId) {
        String sql = "SELECT * FROM cars WHERE car_id = ?";
        return jdbcTemplate.queryForObject(sql,(resultSet, i) -> new Car(resultSet.getLong("car_id"), resultSet.getString("model"), resultSet.getString("mark"), resultSet.getString("color"), LocalDate.parse(resultSet.getString("production_date"))) , carId);
    }

    @Override
    public void update(Car car) {
        String sql = "UPDATE cars SET cars.mark = ?, cars.model = ?, cars.color = ?, cars.production_date = ? WHERE car_id = ?";
        jdbcTemplate.update(sql, car.getMark(), car.getModel(), car.getColor(), car.getProductionDate(), car.getCarId());
    }

    @Override
    public List<Car> findCarsByColor(String color) {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE color = ?";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, color);
        results.forEach(element -> cars.add(new Car(
                Long.parseLong(String.valueOf(element.get("car_id"))),
                String.valueOf(element.get("mark")),
                String.valueOf(element.get("model")),
                String.valueOf(element.get("color")),
                LocalDate.parse(String.valueOf(element.get("production_date")))
        )));
        return cars;
    }

    @Override
    public List<Car> findCarsByProductionDate(String dateFrom, String dateTo) {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE (production_date > ? AND production_date < ?)";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, dateFrom, dateTo);
        results.forEach(element -> cars.add(new Car(
                Long.parseLong(String.valueOf(element.get("car_id"))),
                String.valueOf(element.get("mark")),
                String.valueOf(element.get("model")),
                String.valueOf(element.get("color")),
                LocalDate.parse(String.valueOf(element.get("production_date")))
        )));
        return cars;
    }
}
