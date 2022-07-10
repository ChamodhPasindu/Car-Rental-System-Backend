package lk.ijse.spring.repo;

import lk.ijse.spring.entity.CarReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarReservationRepo extends JpaRepository<CarReservation,String> {

    @Query(value = "SELECT reserve_id FROM car_reservation ORDER BY reserve_id DESC limit 1",nativeQuery = true)
    String generateReservationId();

    @Query(value = "SELECT * FROM car_reservation WHERE reservation_status='Pending'",nativeQuery = true)
    List<CarReservation> getAllPendingReservation();

    @Query(value = "SELECT * FROM car_reservation WHERE reserve_date=current_date()",nativeQuery = true)
    List<CarReservation> getAllTodayReservation();

    @Query(value = "SELECT * FROM car_reservation WHERE pick_up_date=current_date()",nativeQuery = true)
    List<CarReservation> getAllTodayPickUps();


}
