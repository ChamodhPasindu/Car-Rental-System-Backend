package lk.easycar.spring.service;

import lk.easycar.spring.dto.ReservationPaymentDTO;
import lk.easycar.spring.entity.ReservationPayment;

import java.util.List;

public interface ReservationPaymentService {

    String generateReservationBillIdId();

    void makePaymentForReservation(ReservationPaymentDTO reservationPaymentDTO);

    String getIncomeByDate(String type, String start_date, String end_date);

    List<ReservationPaymentDTO> getTodayIncomeList();
}
