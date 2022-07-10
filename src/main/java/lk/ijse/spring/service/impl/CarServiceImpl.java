package lk.ijse.spring.service.impl;

import jdk.nashorn.internal.runtime.regexp.joni.constants.internal.TokenType;
import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.CarReservationDTO;
import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.entity.Car;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.service.CarService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepo carRepo;

    @Autowired
    ModelMapper mapper;
    
    @Override
    public void saveCar(CarDTO carDTO) {
        if (!carRepo.existsById(carDTO.getRegistration_no())) {
            carRepo.save(mapper.map(carDTO, Car.class));
        } else {
            throw new RuntimeException("This Vehicle Already Registered To System..!");
        }
    }

    @Override
    public void updateCar(CarDTO carDTO) {
        if (carRepo.existsById(carDTO.getRegistration_no())) {
            carRepo.save(mapper.map(carDTO, Car.class));
        } else {
            throw new RuntimeException("Update Failed.!  This Vehicle's Previous Record is Missing..Add Again");
        }
    }

    @Override
    public void deleteCar(String id) {
        if (carRepo.existsById(id)) {
            carRepo.deleteById(id);
        } else {
            throw new RuntimeException("Can't Delete.!  This Vehicle's Previous Record is Missing..Add Again");
        }
    }

    @Override
    public CarDTO getCarDetail(String id) {
        if (carRepo.existsById(id)) {
            return mapper.map(carRepo.findById(id).get(), CarDTO.class);
        } else {
            throw new RuntimeException("Can't Get Details.!  This Vehicle's Previous Record is Missing..Add Again");
        }
    }

    @Override
    public List<CarDTO> getAllCarDetail() {
        return mapper.map(carRepo.findAll(), new TypeToken<List<CarDTO>>() {
        }.getType());
    }

    @Override
    public List<CarDTO> getAllTodayPickUps() {
        return mapper.map(carRepo.getAllTodayPickUps(),new TypeToken<List<CarReservationDTO>>(){
        }.getType());
    }

}
