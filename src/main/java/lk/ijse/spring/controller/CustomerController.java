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
import java.net.URISyntaxException;


@RestController
@CrossOrigin
@RequestMapping("controller/customer")
public class CustomerController {


    @Autowired
    CustomerService customerService;



    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil registerCustomer(@RequestPart("file") MultipartFile[] file, @RequestPart("customer") CustomerDTO customerDTO) {


        for (MultipartFile myFile : file) {

            try {
                String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
                File uploadsDir = new File(projectPath + "/uploads");
                uploadsDir.mkdir();
                myFile.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + myFile.getOriginalFilename()));

            } catch (IOException | URISyntaxException e) {
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
                String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
                File uploadsDir = new File(projectPath + "/uploads");
                myFile.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + myFile.getOriginalFilename()));

            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
                return new ResponseUtil(500,"Update Details Failed.Try Again Latter", null);
            }
        }

        customerService.updateCustomer(customerDTO);
        return new ResponseUtil(200, "Update Details Successfully....", null);
    }

    @GetMapping(path = "customerDetail/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getCustomerDetail(@PathVariable String id){
        CustomerDTO customerDTO = customerService.getCustomerDetail(id);

        customerDTO.setLicense_img("uploads/"+customerDTO.getLicense_img());
        customerDTO.setNic_img("uploads/"+customerDTO.getNic_img());

        return new ResponseUtil(200,"Done",customerDTO);
    }

    @GetMapping(path = "AllCustomerDetail",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllCustomerDetail(){
        return new ResponseUtil(200,"Done",customerService.getAllCustomerDetail());
    }

}
