package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.repo.DriverRepo;
import lk.ijse.spring.service.DriverService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepo driverRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public void saveDriver(DriverDTO driverDTO) {
        if (!driverRepo.existsById(driverDTO.getNic())){
            driverRepo.save(mapper.map(driverDTO, Driver.class));
        }else {
            throw new RuntimeException("This Driver Record Already Added To System..!");
        }
    }

    @Override
    public void UpdateDriver(DriverDTO driverDTO) {
        if (driverRepo.existsById(driverDTO.getNic())){
            System.out.println(driverDTO.getNic());
            driverRepo.save(mapper.map(driverDTO, Driver.class));
        }else {
            throw new RuntimeException("Can't Update.!  This Driver's Previous Record is Missing..Add Again");
        }
    }

    @Override
    public void deleteDriver(String id) {
        if (driverRepo.existsById(id)) {
            driverRepo.deleteById(id);
        }else {
            throw new RuntimeException("Can't Update.!  This Driver's Previous Record is Missing..Add Again");
        }
    }
}
