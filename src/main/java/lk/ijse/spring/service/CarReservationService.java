package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarReservationDTO;
import lk.ijse.spring.entity.CarReservation;

import java.util.List;

public interface CarReservationService {

    String generateReservationId();

    void requestReservation(CarReservationDTO carReservationDTO);

    void updateReservationStatus(String id, String status);

    List<CarReservationDTO> getAllPendingReservation();

    CarReservationDTO getReservationDetail(String id);

    List<CarReservationDTO> getAllTodayReservation();

}
