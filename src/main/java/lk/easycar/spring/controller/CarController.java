package lk.easycar.spring.controller;

import lk.easycar.spring.dto.CarDTO;
import lk.easycar.spring.service.CarService;
import lk.easycar.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@CrossOrigin
@RequestMapping("controller/car")
public class CarController {

    @Autowired
    CarService carService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil addCar(@RequestPart("file") MultipartFile[] file, @RequestPart("car") CarDTO carDTO) {
        carService.saveCar(carDTO);

        for (MultipartFile myFile : file) {
            try {
                String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
                File uploadsDir = new File(projectPath + "/uploads");
                uploadsDir.mkdir();
                myFile.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + myFile.getOriginalFilename()));

            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
                return new ResponseUtil(500, "New Vehicle Add Failed.Try Again Latter", null);
            }
        }

        carDTO.getCarImgDetail().setImage_1("uploads/" + carDTO.getCarImgDetail().getImage_1());
        carDTO.getCarImgDetail().setImage_2("uploads/" + carDTO.getCarImgDetail().getImage_2());
        carDTO.getCarImgDetail().setImage_3("uploads/" + carDTO.getCarImgDetail().getImage_3());
        carDTO.getCarImgDetail().setImage_4("uploads/" + carDTO.getCarImgDetail().getImage_4());

        return new ResponseUtil(200, "New Vehicle Registered Successfully...", null);

    }

    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil updateCarDetails(@RequestPart("file") MultipartFile[] file, @RequestPart("car") CarDTO carDTO) {
        carService.updateCar(carDTO);

        for (MultipartFile myFile : file) {
            try {
                String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
                File uploadsDir = new File(projectPath + "/uploads");
                myFile.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + myFile.getOriginalFilename()));

            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
                return new ResponseUtil(500, "Update Vehicle Details Failed.Try Again Latter", null);
            }
        }

        return new ResponseUtil(200, "Update Vehicle Details Successfully...", null);
    }

    //send status of car as available or unavailable by admin.check the status with given id and save
    @PutMapping(params = {"id", "status"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateCarStatusForUnavailableOrAvailable(@RequestParam String id, @RequestParam String status) {
        carService.setCarStatusUnavailableOrAvailable(id, status);
        return new ResponseUtil(200, "Set Car " + id + " As " + status, null);
    }

    @GetMapping(path = "carDetail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getCarDetail(@PathVariable String id) {
        CarDTO carDTO = carService.getCarDetail(id);
        return new ResponseUtil(200, "Done", carDTO);
    }

    @GetMapping(path = "allCarDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllCarDetail() {
        return new ResponseUtil(200, "Done", carService.getAllCarDetail());
    }

    @GetMapping(path = "carsUnderMaintain", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getCarsUnderMaintain() {
        return new ResponseUtil(200, "Done", carService.getCarsUnderMaintain());
    }

    @GetMapping(path = "carsNeedMaintain", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getCarsNeedMaintain() {
        return new ResponseUtil(200, "Done", carService.getCarsNeedMaintain());
    }

    //sent status as Available or Unavailable,Then check it with cars
    @GetMapping(path = "unavailableOrAvailableCarsByStatus/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getUnavailableOrAvailableCars(@PathVariable String status) {
        return new ResponseUtil(200, "Done", carService.getUnavailableOrAvailableCarsByStatus(status));
    }

    //check available and rental vehicles by giving gap of dates
    @GetMapping(path = "availableOrRentalCarsByDate", params = {"pick_up_date", "return_date", "status"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAvailableAndRentalCarsForReservation(@RequestParam String pick_up_date, @RequestParam String return_date, @RequestParam String status) {
        return new ResponseUtil(200, "Done", carService.getAvailableAndRentalCarsForReservation(pick_up_date, return_date, status));
    }

    @DeleteMapping(path = "removeCar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteCarDetail(@PathVariable String id) {
        carService.deleteCar(id);
        return new ResponseUtil(200, "Vehicle Details Deleted Successfully", null);
    }


}
