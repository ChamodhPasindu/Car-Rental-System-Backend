package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarReservationDTO;

import java.util.List;

public interface CarReservationService {

    String generateReservationId();

    void requestReservation(CarReservationDTO carReservationDTO);

    void updateReservationDetail(CarReservationDTO carReservationDTO);

    List<CarReservationDTO> getAllPendingReservation();

    CarReservationDTO getReservationDetail(String id);
}
