package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.entity.Driver;

import java.sql.Date;
import java.util.List;

public interface DriverService {
    void saveDriver(DriverDTO driverDTO);

    void UpdateDriver(DriverDTO driverDTO);

    void deleteDriver(String id);

    DriverDTO getDriverDetail(String id);

    List<DriverDTO> getAllDriverDetail();

    DriverDTO getTodayAvailableAndOccupiedDrivers(String status);

}
