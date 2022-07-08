package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarDTO;

public interface CarService {
    void saveCar(CarDTO carDTO);

    void updateCar(CarDTO carDTO);

    CarDTO getCarDetail(String id);

}
