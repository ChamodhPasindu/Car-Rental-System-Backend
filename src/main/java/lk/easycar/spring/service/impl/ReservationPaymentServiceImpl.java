package lk.easycar.spring.service.impl;

import lk.easycar.spring.dto.ReservationPaymentDTO;
import lk.easycar.spring.entity.CarReservation;
import lk.easycar.spring.entity.ReservationPayment;
import lk.easycar.spring.repo.CarRepo;
import lk.easycar.spring.repo.CarReservationRepo;
import lk.easycar.spring.repo.ReservationPaymentRepo;
import lk.easycar.spring.service.ReservationPaymentService;
import lk.easycar.spring.util.DateFinder;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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
            carReservation.getCar().setMileage(carReservation.getCar().getMileage() + reservationPayment.getNo_of_km());
            carReservation.setReservation_status("Done");
            reservationPayment.setCarReservation(carReservation);
            reservationPaymentRepo.save(reservationPayment);
        } else {
            throw new RuntimeException("Payment Already Done");
        }
    }

    @Override
    public List<ReservationPayment> getIncomeByDate(String type,String start_date,String end_date) {
        if (type.equals("Daily")){
            LocalDate today = DateFinder.getToday();

            List<ReservationPayment> dailyIncome = reservationPaymentRepo.getDailyIncome(today);
            if (!(dailyIncome ==null)){
                return mapper.map(dailyIncome,new TypeToken<List<ReservationPaymentDTO>>(){}.getType());
            }else {
                throw new RuntimeException("Today have No Any Transaction had Been");
            }

        }else if (type.equals("Weekly")){
            LocalDate monthStartDate = DateFinder.getMonthStartDate();
            LocalDate monthEndDate = DateFinder.getMonthEndDate();

            List<ReservationPayment> weeklyIncome = reservationPaymentRepo.getIncomeByDate(monthStartDate, monthEndDate);
            if (!(weeklyIncome ==null)){
                return mapper.map(weeklyIncome,new TypeToken<List<ReservationPaymentDTO>>(){}.getType());
            }else {
                throw new RuntimeException("This Week have No Any Transaction had Been");
            }
        } else if (type.equals("Monthly")) {
            LocalDate monthStartDate = DateFinder.getMonthStartDate();
            LocalDate monthEndDate = DateFinder.getMonthEndDate();

            List<ReservationPayment> monthlyIncome = reservationPaymentRepo.getIncomeByDate(monthStartDate, monthEndDate);
            if (!(monthlyIncome ==null)){
                return mapper.map(monthlyIncome,new TypeToken<List<ReservationPaymentDTO>>(){}.getType());
            }else {
                throw new RuntimeException("This Month have No Any Transaction had Been");
            }
        } else if (type.equals("Yearly")) {
            LocalDate yearStartDate = DateFinder.getYearStartDate();
            LocalDate yearEndDate = DateFinder.getYearEndDate();

            List<ReservationPayment> yearlyIncome = reservationPaymentRepo.getIncomeByDate(yearStartDate, yearEndDate);
            if (!(yearlyIncome ==null)){
                return mapper.map(yearlyIncome,new TypeToken<List<ReservationPaymentDTO>>(){}.getType());
            }else {
                throw new RuntimeException("This Yearly have No Any Transaction had Been");
            }
        }else {
            LocalDate startDate = DateFinder.dateStringToLocalDate(start_date);
            LocalDate endDate = DateFinder.dateStringToLocalDate(end_date);

            List<ReservationPayment> incomeByDate = reservationPaymentRepo.getIncomeByDate(startDate, endDate);
            if (!(incomeByDate ==null)){
                return mapper.map(incomeByDate,new TypeToken<List<ReservationPaymentDTO>>(){}.getType());
            }else {
                throw new RuntimeException("This Dates have No Any Transaction had Been");
            }
        }
    }


}
