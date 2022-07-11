package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.DriverScheduleDTO;
import lk.ijse.spring.entity.DriverSchedule;
import lk.ijse.spring.repo.DriverScheduleRepo;
import lk.ijse.spring.service.DriverScheduleService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return mapper.map(driverScheduleRepo.getDriverSchedulesByDate(start_date,end_date),new TypeToken<List<DriverScheduleDTO>>(){}.getType());
    }
}
