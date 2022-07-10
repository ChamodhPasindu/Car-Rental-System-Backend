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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

@CrossOrigin
@RestController
@RequestMapping("controller/reservation")
public class ReservationController {

    @Autowired
    CarReservationService carReservationService;

    @Autowired
    DriverService driverService;

    @Autowired
    DriverScheduleService driverScheduleService;

    @GetMapping(path = "generateReservationId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil generateReservationId() {
        return new ResponseUtil(200, "Done", carReservationService.generateReservationId());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil requestReservation(@RequestPart("reservation") CarReservationDTO carReservation, @RequestPart("file") MultipartFile file) {

        try {
            String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
            File uploadsDir = new File(projectPath + "/uploads");
            file.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + file.getOriginalFilename()));

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return new ResponseUtil(500, "Reservation Sending Filed.Try Again Latter", null);
        }
        carReservation.setBank_slip_img("uploads/"+carReservation.getBank_slip_img());
        carReservationService.requestReservation(carReservation);
        return new ResponseUtil(200, "Request Send Successfully", null);
    }

    @PutMapping(params = {"id","status"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateReservationStatus(@RequestParam String id,@RequestParam String status) {
        carReservationService.updateReservationStatus(id,status);
        return new ResponseUtil(200, status+ "Request Successfully", null);
    }

    @GetMapping(path = "pendingReservation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllPendingReservation() {
        return new ResponseUtil(200, "Request Send Successfully", carReservationService.getAllPendingReservation());
    }

    @GetMapping(path = "getReservation/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getReservationDetail(@PathVariable String id) {
        return new ResponseUtil(200, "Done", carReservationService.getReservationDetail(id));
    }

    @GetMapping(path = "todayReservation",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllTodayReservation(){
        return new ResponseUtil(200, "Done",carReservationService.getAllTodayReservation());
    }

    @GetMapping(path = "todayPickUps",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllTodayPickUps(){
        return new ResponseUtil(200, "Done",carReservationService.getAllTodayPickUps());
    }





}
