package lk.ijse.spring.repo;

import lk.ijse.spring.entity.CarReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarReservationRepo extends JpaRepository<CarReservation,String> {

    @Query(value = "SELECT reserve_id FROM car_reservation ORDER BY reserve_id DESC limit 1",nativeQuery = true)
    String generateReservationId();
}
