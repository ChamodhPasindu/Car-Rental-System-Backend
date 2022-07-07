package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.entity.Car;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepo carRepo;

    @Autowired
    ModelMapper mapper;


    @Override
    public void saveCar(CarDTO carDTO) {
        if (!carRepo.existsById(carDTO.getRegistration_no())){
            carRepo.save(mapper.map(carDTO, Car.class));
        }else {
            throw new RuntimeException("This Vehicle Already Registered To System..!");
        }
    }

    @Override
    public void updateCar(CarDTO carDTO) {
        if (carRepo.existsById(carDTO.getRegistration_no())){
            carRepo.save(mapper.map(carDTO, Car.class));
        }else {
            throw new RuntimeException("Something wrong.!  This Vehicle's Previous Record is Missing..Add Again");
        }
    }
}
