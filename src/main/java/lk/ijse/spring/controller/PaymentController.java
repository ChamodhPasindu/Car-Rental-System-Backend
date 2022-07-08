package lk.ijse.spring.controller;

import lk.ijse.spring.service.ReservationPaymentService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("controller/payment")
public class PaymentController {


    @Autowired
    ReservationPaymentService reservationPaymentService;

    @GetMapping(path = "generateBillId",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil generateReservationId(){
        return new ResponseUtil(200,"Done",reservationPaymentService.generateReservationBillIdId());
    }
}
