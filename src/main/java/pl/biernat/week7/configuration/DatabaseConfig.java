package pl.biernat.week7.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.biernat.week7.model.Car;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DatabaseConfig {

    private final DataSource dataSource;

    @Autowired
    public DatabaseConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void initDb() {
        String dropTableCars = "DROP TABLE IF EXISTS cars";
        String dropTableNews = "DROP TABLE IF EXISTS news";
        jdbcTemplate().update(dropTableCars);
        jdbcTemplate().update(dropTableNews);

        String createTableCars = "CREATE TABLE cars (car_id int NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                "mark varchar (100) , model varchar (100) , color varchar (50) , " +
                "production_date varchar (50))";
        jdbcTemplate().update(createTableCars);

        String createTableNews = "CREATE TABLE news (news_id int NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                "author varchar (255) , title varchar (255) , description varchar (500) , " +
                "content varchar (500))";
        jdbcTemplate().update(createTableNews);

        String sql = "INSERT INTO cars (car_id, mark, model, color, production_date) VALUES (?, ?, ?, ?, ?)";
        initService().forEach(car ->
                jdbcTemplate().update(sql, car.getCarId(), car.getMark(), car.getModel(), car.getColor(),
                        Date.valueOf(car.getProductionDate())));
    }

    private List<Car> initService() {
        List<Car> initDbList = new ArrayList<>();
        initDbList.add(new Car(1L, "Fiat", "126p", "GREEN", LocalDate.of(1978, 01, 30)));
        initDbList.add(new Car(2L, "Opel", "Corsa", "YELLOW", LocalDate.of(1999, 01, 14)));
        initDbList.add(new Car(3L, "Ford", "C-max", "BLUE", LocalDate.of(2009, 02, 25)));
        initDbList.add(new Car(4L, "Skoda", "Fabia", "RED", LocalDate.of(1995, 07, 13)));
        initDbList.add(new Car(5L, "Jeep", "Compas", "RED", LocalDate.of(2019, 10, 05)));
        initDbList.add(new Car(6L, "BMW", "X5", "BLACK", LocalDate.of(2020, 05, 18)));
        initDbList.add(new Car(7L, "Honda", "Jazz", "GREEN", LocalDate.of(2001, 06, 27)));
        initDbList.add(new Car(8L, "Lamborghini", "Urus", "YELLOW", LocalDate.of(2020, 04, 13)));
        initDbList.add(new Car(9L, "Kia", "Stringer", "BLACK", LocalDate.of(2020, 12, 29)));
        initDbList.add(new Car(10L, "Ford", "Kuga", "GREEN", LocalDate.of(2008, 10, 22)));
        initDbList.add(new Car(11L, "Honda", "Civic", "RED", LocalDate.of(2011, 05, 07)));

        return initDbList;
    }
}
