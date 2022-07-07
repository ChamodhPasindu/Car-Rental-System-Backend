package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.service.CustomerService;

import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
@CrossOrigin
@RequestMapping("controller/customer")
public class CustomerController {


    @Autowired
    CustomerService customerService;

    @Autowired
    Environment env;


    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity registerCustomer(@RequestPart("file") MultipartFile[] file, @RequestPart("customer") CustomerDTO customerDTO) {

        for (MultipartFile myFile : file) {

            try {
                String projectPath = env.getRequiredProperty("customer.image.path");
                File uploadsDir = new File(projectPath + "/uploads");
                uploadsDir.mkdir();
                myFile.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + myFile.getOriginalFilename()));

            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity(new ResponseUtil(200, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        customerService.saveCustomer(customerDTO);
        return new ResponseEntity(new ResponseUtil(200, "Registration Successfully....", null), HttpStatus.CREATED);
    }

}
