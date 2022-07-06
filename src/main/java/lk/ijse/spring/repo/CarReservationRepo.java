package lk.ijse.spring.repo;

import lk.ijse.spring.entity.CarReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarReservationRepo extends JpaRepository<CarReservation,String> {
}
