package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarReservationDTO;

public interface CarReservationService {

    String generateReservationId();

    void requestReservation(CarReservationDTO carReservationDTO);
}
