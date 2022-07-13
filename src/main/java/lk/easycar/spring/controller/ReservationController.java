package lk.easycar.spring.controller;

import lk.easycar.spring.dto.CarReservationDTO;
import lk.easycar.spring.service.CarReservationService;
import lk.easycar.spring.service.DriverScheduleService;
import lk.easycar.spring.service.DriverService;
import lk.easycar.spring.util.ResponseUtil;
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

    //When send reservation request,waiver payment transcript photo and details came as separate Multipart file
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil requestReservation(@RequestPart("reservation") CarReservationDTO carReservation, @RequestPart("file") MultipartFile file) {
        carReservationService.requestReservation(carReservation);

        try {
            String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
            File uploadsDir = new File(projectPath + "/uploads");
            file.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + file.getOriginalFilename()));

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return new ResponseUtil(500, "Reservation Sending Filed.Try Again Latter", null);
        }
        carReservation.setBank_slip_img("uploads/" + carReservation.getBank_slip_img());
        return new ResponseUtil(200, "Request Send Successfully", null);
    }

    //update reservation Status and Driver by reserve_id
    @PutMapping(params = {"reserve_id", "driver_id", "status"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateReservationStatus(@RequestParam String reserve_id, @RequestParam String driver_id, @RequestParam String status) {
        carReservationService.updateReservationStatus(reserve_id, driver_id, status);
        return new ResponseUtil(200, status + " Request Successfully", null);
    }

    //return reservations they are in pending status
    @GetMapping(path = "pendingReservation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllPendingReservation() {
        return new ResponseUtil(200, "Done", carReservationService.getAllPendingReservation());
    }

    @GetMapping(path = "getReservation/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getReservationDetail(@PathVariable String id) {
        return new ResponseUtil(200, "Done", carReservationService.getReservationDetail(id));
    }

    //return all today reserve reservations
    @GetMapping(path = "todayReservation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllTodayReservation() {
        return new ResponseUtil(200, "Done", carReservationService.getAllTodayReservation());
    }

    //return all reservations they are picking up today
    @GetMapping(path = "todayPickUps", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllTodayPickUps() {
        return new ResponseUtil(200, "Done", carReservationService.getAllTodayPickUps());
    }
}
