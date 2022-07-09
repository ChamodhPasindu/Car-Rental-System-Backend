package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CarReservationDTO;
import lk.ijse.spring.entity.CarReservation;
import lk.ijse.spring.repo.CarReservationRepo;
import lk.ijse.spring.service.CarReservationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.xml.ws.Action;
import java.util.List;

@Service
@Transactional
public class CarReservationServiceImpl implements CarReservationService {
    @Autowired
    CarReservationRepo carReservationRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public String generateReservationId() {
        String id = carReservationRepo.generateReservationId();
        if (!(id == null)) {
            int tempId = Integer.parseInt(id.split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "RID-000" + tempId;

            } else if (tempId <= 99) {
                return "RID-00" + tempId;

            } else if (tempId <= 999) {
                return "RID-0" + tempId;

            } else {
                return "RID-" + tempId;
            }
        } else {
            return "RID-0001";
        }
    }

    @Override
    public void requestReservation(CarReservationDTO carReservationDTO) {
        if (!carReservationRepo.existsById(carReservationDTO.getReserve_id())) {
            carReservationRepo.save(mapper.map(carReservationDTO, CarReservation.class));
        } else {
            throw new RuntimeException("Your Reservation Request can't Send in this moment,Try Again..!");
        }
    }

    @Override
    public void updateReservationDetail(CarReservationDTO carReservationDTO) {
        if (carReservationRepo.existsById(carReservationDTO.getReserve_id())) {
            carReservationRepo.save(mapper.map(carReservationDTO, CarReservation.class));
        } else {
            throw new RuntimeException("Update Failed,This Reservation Previous Record is Missing.Try Again..!");
        }
    }

    @Override
    public List<CarReservationDTO> getAllPendingReservation() {
        return mapper.map(carReservationRepo.getAllPendingReservation(),new TypeToken<List<CarReservationDTO>>(){}.getType());
    }

    @Override
    public CarReservationDTO getReservationDetail(String id) {
        if (carReservationRepo.existsById(id)) {
            return mapper.map(carReservationRepo.findById(id).get(),CarReservationDTO.class);
        }else {
            throw new RuntimeException("Can't Get Details..!  This Reservation Previous Record is Missing.Try Again..!");
        }
    }
}
