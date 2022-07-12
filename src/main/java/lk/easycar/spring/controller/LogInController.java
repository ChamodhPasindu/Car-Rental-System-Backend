package lk.easycar.spring.controller;

import lk.easycar.spring.dto.AdminDTO;
import lk.easycar.spring.dto.CustomerDTO;
import lk.easycar.spring.dto.UserDTO;
import lk.easycar.spring.service.AdminService;
import lk.easycar.spring.service.CustomerService;
import lk.easycar.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("controller/login")
public class LogInController {

    @Autowired
    CustomerService customerService;

    @Autowired
    AdminService adminService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil checkUserNameAndPassword(@RequestBody UserDTO userDTO) {

        CustomerDTO customerDTO = customerService.checkCustomerLogIn(userDTO.getUser_name(), userDTO.getPassword());
        if (customerDTO == null) {
            AdminDTO adminDTO = adminService.checkAdminLogIn(userDTO.getUser_name(), userDTO.getPassword());
            if (adminDTO == null) {
                return new ResponseUtil(200, "Incorrect username and password", null);
            } else {
                return new ResponseUtil(200, "Admin Login Successfully....", adminDTO);
            }
        } else {
            return new ResponseUtil(200, "Customer Login Successfully....", customerDTO);
        }
    }

}
