package lk.ijse.spring.controller;

import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.service.CarService;
import lk.ijse.spring.service.DriverService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("controller/driver")
public class DriverController {


    @Autowired
    DriverService driverService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveDriver(@ModelAttribute DriverDTO driverDTO){
        System.out.println(driverDTO.toString());
        driverService.saveDriver(driverDTO);
        return new ResponseUtil(200,"Driver Added Successfully",null);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseUtil updateDriverDetail(@RequestBody DriverDTO driverDTO){
        System.out.println(driverDTO.toString());
        driverService.UpdateDriver(driverDTO);
        return new ResponseUtil(200,"Driver Updated Successfully",null);

    }

    @DeleteMapping(path = "removeDriver/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteDriverDetail(@PathVariable String id){
        driverService.deleteDriver(id);
        return new ResponseUtil(200,"Driver Details Deleted Successfully",null);

    }

}
