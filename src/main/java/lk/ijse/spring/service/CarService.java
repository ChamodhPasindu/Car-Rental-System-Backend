package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.CarReservationDTO;
import lk.ijse.spring.entity.Car;

import java.util.List;

public interface CarService {
    void saveCar(CarDTO carDTO);

    void updateCar(CarDTO carDTO);

    void deleteCar(String id);

    CarDTO getCarDetail(String id);

    List<CarDTO> getAllCarDetail();

    List<CarDTO> getCarsUnderMaintain();

    List<CarDTO> getCarsNeedMaintain();

    List<CarDTO> getUnavailableOrAvailableCarsByStatus(String status);

    List<CarDTO> getAvailableAndRentalCarsForReservation(String pick_up_date,String return_date,String status);

    void setCarStatusUnavailableOrAvailable(String id,String status);



}
