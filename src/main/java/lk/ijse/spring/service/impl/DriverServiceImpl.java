package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.repo.DriverRepo;
import lk.ijse.spring.repo.DriverScheduleRepo;
import lk.ijse.spring.service.DriverService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepo driverRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public void saveDriver(DriverDTO driverDTO) {
        if (!driverRepo.existsById(driverDTO.getNic())) {
            driverRepo.save(mapper.map(driverDTO, Driver.class));
        } else {
            throw new RuntimeException("This Driver Record Already Added To System..!");
        }
    }

    @Override
    public void UpdateDriver(DriverDTO driverDTO) {
        if (driverRepo.existsById(driverDTO.getNic())) {
            driverRepo.save(mapper.map(driverDTO, Driver.class));
        } else {
            throw new RuntimeException("Update Failed.!  This Driver's Previous Record is Missing..Add Again");
        }
    }

    @Override
    public void deleteDriver(String id) {
        if (driverRepo.existsById(id)) {
            driverRepo.deleteById(id);
        } else {
            throw new RuntimeException("Can't Delete.!  This Driver's Previous Record is Missing..Add Again");
        }
    }

    @Override
    public DriverDTO getDriverDetail(String id) {
        if (driverRepo.existsById(id)) {
            return mapper.map(driverRepo.findById(id).get(), DriverDTO.class);
        }
        throw new RuntimeException("Can't Get Details.!  This Driver's Previous Record is Missing..Add Again");
    }

    @Override
    public List<DriverDTO> getAllDriverDetail() {
        return mapper.map(driverRepo.findAll(), new TypeToken<List<DriverDTO>>() {
        }.getType());
    }

    @Override
    public DriverDTO getTodayAvailableAndOccupiedDrivers(String status) {
        switch (status) {
            case "Available":return mapper.map(driverRepo.getTodayAvailableDrivers(),DriverDTO.class);
            case "Occupied":return mapper.map(driverRepo.getTodayOccupiedDrivers(),DriverDTO.class);
            default:throw new RuntimeException("Not select status for show record of Available or Occupied drivers");
        }
    }


}
