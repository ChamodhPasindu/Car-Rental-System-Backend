package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CarReservationDTO;
import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.dto.DriverScheduleDTO;
import lk.ijse.spring.service.CarReservationService;
import lk.ijse.spring.service.DriverScheduleService;
import lk.ijse.spring.service.DriverService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("controller/reservation")
public class ReservationController {

    @Autowired
    CarReservationService carReservationService;

    @Autowired
    DriverService driverService;

    @GetMapping(path = "generateReservationId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil generateReservationId() {
        return new ResponseUtil(200, "Done", carReservationService.generateReservationId());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil requestReservation(@RequestBody CarReservationDTO carReservationDTO) {
        if (carReservationDTO.getDriver_status().equals("YES")) {

            DriverDTO driverDTO = driverService.selectDriverForReservation(
                    carReservationDTO.getPick_up_date(),
                    carReservationDTO.getReturn_date());

            DriverScheduleDTO driverScheduleDTO = new DriverScheduleDTO(
                    carReservationDTO.getPick_up_time(),
                    carReservationDTO.getPick_up_date(),
                    carReservationDTO.getReturn_date(),
                    driverDTO);

            carReservationDTO.setDriverSchedule(driverScheduleDTO);

        }
        carReservationService.requestReservation(carReservationDTO);
        return new ResponseUtil(200, "Request Send Successfully", null);

    }

}
