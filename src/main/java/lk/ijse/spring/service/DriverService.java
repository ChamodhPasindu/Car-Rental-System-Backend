package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.DriverDTO;

import java.sql.Date;

public interface DriverService {
    void saveDriver(DriverDTO driverDTO);

    void UpdateDriver(DriverDTO driverDTO);

    void deleteDriver(String id);

    DriverDTO selectDriverForReservation(Date pick_date, Date return_date);


}
