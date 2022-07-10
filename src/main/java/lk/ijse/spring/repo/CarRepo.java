package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Car;
import lk.ijse.spring.entity.CarReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepo extends JpaRepository<Car,String> {

    @Query(value = "SELECT * FROM car_reservation WHERE pick_up_date=current_date()",nativeQuery = true)
    List<Car> getAllTodayPickUps();
}
