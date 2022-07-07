package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.service.CarService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("controller/car")
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    Environment env;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil addCar(@RequestPart("file") MultipartFile[] file, @RequestPart("car") CarDTO carDTO) {

        System.out.println(carDTO.toString()+" "+carDTO.getCarImgDetailDTO().toString());

        for (MultipartFile myFile : file) {
            System.out.println(myFile.getOriginalFilename());
            try {
                String projectPath = env.getRequiredProperty("vehicle.image.path");
                File uploadsDir = new File(projectPath + "/uploads");
                uploadsDir.mkdir();
                myFile.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + myFile.getOriginalFilename()));

            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseUtil(500, "New Vehicle Add Failed.Try Again Latter", null);
            }
        }

        carService.saveCar(carDTO);
        return new ResponseUtil(200, "New Vehicle Registered Successfully...", null);

    }


}
