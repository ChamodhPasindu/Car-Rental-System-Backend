package lk.ijse.spring.controller;

import lk.ijse.spring.service.CarReservationService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("controller/reservation")
public class ReservationController {

    @Autowired
    CarReservationService carReservationService;

    @GetMapping(path = "generateReservationId",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil generateReservationId(){
        return new ResponseUtil(200,"Done",carReservationService.generateReservationId());
    }

}
