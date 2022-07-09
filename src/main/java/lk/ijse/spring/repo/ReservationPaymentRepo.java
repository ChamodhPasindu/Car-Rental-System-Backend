package lk.ijse.spring.repo;

import lk.ijse.spring.entity.ReservationPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationPaymentRepo extends JpaRepository<ReservationPayment,String> {

    @Query(value = "SELECT bill_id FROM reservation_payment ORDER BY bill_id DESC limit 1",nativeQuery = true)
    String generateReservationBillId();
}
