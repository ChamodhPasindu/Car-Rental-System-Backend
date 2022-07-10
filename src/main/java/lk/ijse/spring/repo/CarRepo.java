package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Car;
import lk.ijse.spring.entity.CarReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepo extends JpaRepository<Car,String> {

    @Query(value = "SELECT * FROM car_reservation WHERE pick_up_date=current_date()",nativeQuery = true)
    List<Car> getAllTodayPickUps();

    @Query(value = "SELECT * FROM car WHERE status='UnderMaintain'",nativeQuery = true)
    List<Car> getCarsUnderMaintain();

    @Query(value = "SELECT * FROM car WHERE status='Unavailable'",nativeQuery = true)
    List<Car> getUnavailableCars();

    @Query(value = "SELECT * FROM car WHERE status='Available'",nativeQuery = true)
    List<Car> getAvailableCars();

    @Query(value = "SELECT * FROM car WHERE MOD(mileage,5000) = 0",nativeQuery = true)
    List<Car> getCarsNeedMaintain();

}
