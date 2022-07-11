package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Car;
import lk.ijse.spring.entity.CarReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepo extends JpaRepository<Car, String> {

    @Query(value = "SELECT * FROM car WHERE status='UnderMaintain'", nativeQuery = true)
    List<Car> getCarsUnderMaintain();

    @Query(value = "SELECT * FROM car WHERE status='Unavailable'", nativeQuery = true)
    List<Car> getUnavailableCars();

    @Query(value = "SELECT * FROM car WHERE status='Available'", nativeQuery = true)
    List<Car> getAvailableCars();

    @Query(value = "SELECT * FROM car WHERE MOD(mileage,5000) = 0", nativeQuery = true)
    List<Car> getCarsNeedMaintain();

    @Query(value = "SELECT * FROM car WHERE registration_no NOT IN (SELECT DISTINCT registration_no FROM car_reservation WHERE pick_up_date BETWEEN ?1 AND ?2 OR return_date BETWEEN ?1 AND ?2) AND status='Available'", nativeQuery = true)
    List<Car> getAvailableCarsForReservation(String pick_date, String return_date);

    @Query(value = "SELECT * FROM car WHERE registration_no NOT IN (SELECT DISTINCT registration_no FROM car_reservation WHERE ?1 BETWEEN pick_up_date AND return_date) AND status='Available'", nativeQuery = true)
    List<Car> getAvailableCarsForReservationOnDay(String select_date);

    @Query(value = "SELECT * FROM car WHERE registration_no IN (SELECT DISTINCT registration_no FROM car_reservation WHERE pick_up_date BETWEEN ?1 AND ?2 OR return_date BETWEEN ?1 AND ?2) AND status='Available'", nativeQuery = true)
    List<Car> getRentalCars(String pick_up_date, String return_date);

    @Query(value = "SELECT * FROM car WHERE registration_no IN (SELECT DISTINCT registration_no FROM car_reservation WHERE ?1 BETWEEN pick_up_date AND return_date) AND status='Available'", nativeQuery = true)
    List<Car> getRentalCarsOnDay(String select_date);

}
