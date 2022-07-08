package lk.ijse.spring.repo;

import lk.ijse.spring.entity.ReservationPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationPaymentRepo extends JpaRepository<ReservationPayment,String> {

    @Query(value = "select rp.bill_id from reservation_payment rp order by rp.bill_id desc limit 1",nativeQuery = true)
    String generateReservationBillId();
}
