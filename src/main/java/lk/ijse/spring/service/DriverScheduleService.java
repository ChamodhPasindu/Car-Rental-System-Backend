package lk.ijse.spring.service;

import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.dto.DriverScheduleDTO;
import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.entity.DriverSchedule;

import java.sql.Date;
import java.util.List;

public interface DriverScheduleService {
    List<DriverScheduleDTO> getDriverSchedulesByDate(String start_date, String end_date);


}
