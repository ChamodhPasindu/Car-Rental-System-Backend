package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.service.CarService;
import lk.ijse.spring.util.ResponseUtil;
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

        carService.saveCar(carDTO);
        return new ResponseUtil(200, "New Vehicle Registered Successfully...", null);

    }

    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil updateCarDetails(@RequestPart("file") MultipartFile[] file, @RequestPart("car") CarDTO carDTO) {

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

        carService.updateCar(carDTO);
        return new ResponseUtil(200, "Update Vehicle Details Successfully...", null);
    }

    @PutMapping(params = {"id", "status"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateCarStatusForUnavailable(@RequestParam String id, @RequestParam String status) {
        carService.setCarStatusUnavailable(id, status);
        return new ResponseUtil(200, "Set Car" + id + " As " + status, null);
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

    @GetMapping(path = "unavailableCars", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getUnavailableCars() {
        return new ResponseUtil(200, "Done", carService.getUnavailableCars());
    }

    @GetMapping(path = "AvailableCars", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAvailableCars() {
        return new ResponseUtil(200, "Done", carService.getAvailableCars());
    }

    @DeleteMapping(path = "removeCar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteCarDetail(@PathVariable String id) {
        carService.deleteCar(id);
        return new ResponseUtil(200, "Vehicle Details Deleted Successfully", null);
    }


}
