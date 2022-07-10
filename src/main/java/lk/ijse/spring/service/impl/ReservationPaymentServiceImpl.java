package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.ReservationPaymentDTO;
import lk.ijse.spring.entity.Car;
import lk.ijse.spring.entity.CarReservation;
import lk.ijse.spring.entity.ReservationPayment;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.repo.CarReservationRepo;
import lk.ijse.spring.repo.ReservationPaymentRepo;
import lk.ijse.spring.service.ReservationPaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class ReservationPaymentServiceImpl implements ReservationPaymentService {

    @Autowired
    ReservationPaymentRepo reservationPaymentRepo;

    @Autowired
    CarReservationRepo carReservationRepo;

    @Autowired
    CarRepo carRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public String generateReservationBillIdId() {
        String id = reservationPaymentRepo.generateReservationBillId();
        if (!(id == null)) {
            int tempId = Integer.parseInt(id.split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "BID-000" + tempId;

            } else if (tempId <= 99) {
                return "BID-00" + tempId;

            } else if (tempId <= 999) {
                return "BID-0" + tempId;

            } else {
                return "BID-" + tempId;
            }
        } else {
            return "BID-0001";
        }
    }

    @Override
    public void makePaymentForReservation(ReservationPaymentDTO reservationPaymentDTO) {
        if (!reservationPaymentRepo.existsById(reservationPaymentDTO.getBill_id())) {
            CarReservation carReservation = carReservationRepo.findById(reservationPaymentDTO.getCarReservation().getReserve_id()).get();
            ReservationPayment reservationPayment = mapper.map(reservationPaymentDTO, ReservationPayment.class);
            carReservation.getCar().setMileage(carReservation.getCar().getMileage()+reservationPayment.getNo_of_km());
            reservationPayment.setCarReservation(carReservation);
            reservationPaymentRepo.save(reservationPayment);
        } else {
            throw new RuntimeException("Payment Already Done");
        }
    }
}
