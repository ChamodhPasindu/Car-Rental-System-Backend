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


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil registerCustomer(@RequestPart("file") MultipartFile[] file, @RequestPart("customer") CustomerDTO customerDTO) {

        for (MultipartFile myFile : file) {

            try {
                String projectPath = env.getRequiredProperty("customer.image.path");
                File uploadsDir = new File(projectPath + "/uploads");
                uploadsDir.mkdir();
                myFile.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + myFile.getOriginalFilename()));

            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseUtil(500,"Registration Failed.Try Again Latter", null);
            }
        }

        customerService.saveCustomer(customerDTO);
        return new ResponseUtil(200, "Registration Successfully....", null);
    }

    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil updateCustomerDetails(@RequestPart("file") MultipartFile[] file, @RequestPart("customer") CustomerDTO customerDTO){

        for (MultipartFile myFile : file) {
            System.out.println(myFile);
            try {
                File uploadsDir = new File(env.getRequiredProperty("customer.image.path") + "/uploads");
                myFile.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + myFile.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseUtil(500,"Update Details Failed.Try Again Latter", null);
            }
        }

        customerService.updateCustomer(customerDTO);
        return new ResponseUtil(200, "Update Details Successfully....", null);
    }

}
