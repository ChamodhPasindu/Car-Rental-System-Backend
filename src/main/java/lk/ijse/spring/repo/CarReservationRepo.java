package lk.ijse.spring.repo;

import lk.ijse.spring.entity.CarReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarReservationRepo extends JpaRepository<CarReservation,String> {

    @Query(value = "select r.reserve_id from car_reservation r order by r.reserve_id desc limit 1",nativeQuery = true)
    String generateReservationId();
}
