package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarReservationDTO;
import lk.ijse.spring.dto.ReservationPaymentDTO;

public interface ReservationPaymentService {

    String generateReservationBillIdId();

    void makePaymentForReservation(ReservationPaymentDTO reservationPaymentDTO);

}
