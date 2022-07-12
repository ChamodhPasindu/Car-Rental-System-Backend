package lk.easycar.spring.repo;

import lk.easycar.spring.entity.ReservationPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationPaymentRepo extends JpaRepository<ReservationPayment,String> {

    @Query(value = "SELECT bill_id FROM reservation_payment ORDER BY bill_id DESC limit 1",nativeQuery = true)
    String generateReservationBillId();


    @Query(value = "SELECT * FROM reservation_payment WHERE pay_date BETWEEN ?1 AND ?2",nativeQuery = true)
    List<ReservationPayment> getIncomeByDate(LocalDate first_date, LocalDate last_date);

    @Query(value = "SELECT * FROM reservation_payment WHERE pay_date=?1",nativeQuery = true)
    List<ReservationPayment> getDailyIncome(LocalDate date);


}
