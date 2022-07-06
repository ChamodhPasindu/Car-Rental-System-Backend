package lk.ijse.spring.repo;

import lk.ijse.spring.entity.ReservationPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationPaymentRepo extends JpaRepository<ReservationPayment,String> {
}
