package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarReservationDTO;
import lk.ijse.spring.dto.ReservationPaymentDTO;
import lk.ijse.spring.entity.ReservationPayment;

import java.util.List;

public interface ReservationPaymentService {

    String generateReservationBillIdId();

    void makePaymentForReservation(ReservationPaymentDTO reservationPaymentDTO);

    List<ReservationPayment>getIncomeByDate(String type,String start_date,String end_date);

}
