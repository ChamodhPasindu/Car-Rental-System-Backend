package lk.ijse.spring.service;

import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.dto.DriverScheduleDTO;
import lk.ijse.spring.entity.Driver;

import java.sql.Date;

public interface DriverScheduleService {

    void makeSchedule(DriverScheduleDTO driverScheduleDTO);

}
