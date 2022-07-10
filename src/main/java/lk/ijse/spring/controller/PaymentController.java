package lk.ijse.spring.controller;

import lk.ijse.spring.dto.ReservationPaymentDTO;
import lk.ijse.spring.service.ReservationPaymentService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("controller/payment")
public class PaymentController {

    @Autowired
    ReservationPaymentService reservationPaymentService;

    @GetMapping(path = "generateBillId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil generatePaymentId() {
        return new ResponseUtil(200, "Done", reservationPaymentService.generateReservationBillIdId());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil makePaymentForReservation(@RequestBody ReservationPaymentDTO reservationPaymentDTO) {
        reservationPaymentService.makePaymentForReservation(reservationPaymentDTO);
        return new ResponseUtil(200, "Transaction Successfully", null);
    }
}
