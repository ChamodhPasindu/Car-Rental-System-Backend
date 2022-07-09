package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.DriverScheduleDTO;
import lk.ijse.spring.entity.DriverSchedule;
import lk.ijse.spring.repo.DriverScheduleRepo;
import lk.ijse.spring.service.DriverScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DriverScheduleServiceImpl implements DriverScheduleService {

    @Autowired
    DriverScheduleRepo driverScheduleRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public void makeSchedule(DriverScheduleDTO driverScheduleDTO) {
        driverScheduleRepo.save(mapper.map(driverScheduleDTO, DriverSchedule.class));
    }
}
