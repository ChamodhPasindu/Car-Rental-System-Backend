package lk.easycar.spring.service.impl;

import lk.easycar.spring.dto.AdminDTO;
import lk.easycar.spring.dto.DriverDTO;
import lk.easycar.spring.entity.Admin;
import lk.easycar.spring.entity.Driver;
import lk.easycar.spring.repo.DriverRepo;
import lk.easycar.spring.service.DriverService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepo driverRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public DriverDTO checkDriverLogIn(String name, String password) {
        Driver driver = driverRepo.checkDriverLogIn(name, password);
        if (!(driver ==null)){
            return mapper.map(driver, DriverDTO.class);
        }else {
            return null;
        }
    }

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
