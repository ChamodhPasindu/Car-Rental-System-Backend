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
    public DriverDTO selectDriverForReservation(Date pick_date, Date return_date) {
        Driver driver = driverRepo.selectDriverForReservation(pick_date, return_date);
        if (!(driver == null)) {
            return mapper.map(driver, DriverDTO.class);
        } else {
            throw new RuntimeException("Sorry,Drivers are not Available in This Moment..");
        }
    }
}
