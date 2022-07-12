package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.DriverScheduleDTO;
import lk.ijse.spring.entity.DriverSchedule;
import lk.ijse.spring.entity.ReservationPayment;
import lk.ijse.spring.repo.DriverScheduleRepo;
import lk.ijse.spring.service.DriverScheduleService;
import lk.ijse.spring.util.DateFinder;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class DriverScheduleServiceImpl implements DriverScheduleService {

    @Autowired
    DriverScheduleRepo driverScheduleRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<DriverScheduleDTO> getDriverSchedulesByDate(String start_date, String end_date) {
        return mapper.map(driverScheduleRepo.getDriverSchedulesByDate(start_date, end_date), new TypeToken<List<DriverScheduleDTO>>() {
        }.getType());
    }

    @Override
    public List<DriverScheduleDTO> getDriverWeeklyScheduleByDate(String driver_id, String date_rage) {
        if (date_rage.equals("Monthly")) {
            LocalDate monthStartDate = DateFinder.getMonthStartDate();
            LocalDate monthEndDate = DateFinder.getMonthEndDate();

            List<DriverSchedule> schedule = driverScheduleRepo.getDriverWeeklyOrMonthlyScheduleByDate(driver_id, monthStartDate, monthEndDate);
            if (!schedule.isEmpty()) {
                return mapper.map(schedule, new TypeToken<List<DriverScheduleDTO>>() {
                }.getType());
            } else {
                throw new RuntimeException("You Have Not any Rides In this Month ");
            }
        } else if (date_rage.equals("Weekly")) {
            LocalDate weekStartDate = DateFinder.getWeekStartDate();
            LocalDate weekEndDate = DateFinder.getWeekEndDate();
            System.out.println(weekStartDate+" "+weekEndDate);
            List<DriverSchedule> schedule = driverScheduleRepo.getDriverWeeklyOrMonthlyScheduleByDate(driver_id, weekStartDate, weekEndDate);
            if (!schedule.isEmpty()) {
                return mapper.map(schedule, new TypeToken<List<DriverScheduleDTO>>() {
                }.getType());
            } else {
                throw new RuntimeException("You Have Not any Rides In this Month ");
            }
        } else {
            throw new RuntimeException("Please Select Monthly or Annually Schedule");
        }

    }
}
