package lk.easycar.spring.controller;

import lk.easycar.spring.dto.AdminDTO;
import lk.easycar.spring.dto.CustomerDTO;
import lk.easycar.spring.dto.DriverDTO;
import lk.easycar.spring.dto.UserDTO;
import lk.easycar.spring.service.AdminService;
import lk.easycar.spring.service.CustomerService;
import lk.easycar.spring.service.DriverService;
import lk.easycar.spring.util.PasswordEncryptor;
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

    @Autowired
    DriverService driverService;

    @Autowired
    PasswordEncryptor passwordEncryptor;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil checkUserNameAndPassword(@RequestBody UserDTO userDTO) {

        String encryptedPassword = passwordEncryptor.getPassword(userDTO.getPassword());

        CustomerDTO customerDTO = customerService.checkCustomerLogIn(userDTO.getUser_name(), encryptedPassword);
        if (customerDTO == null) {
            AdminDTO adminDTO = adminService.checkAdminLogIn(userDTO.getUser_name(),encryptedPassword);
            if (adminDTO == null) {
                DriverDTO driverDTO = driverService.checkDriverLogIn(userDTO.getUser_name(), encryptedPassword);
                if (!(driverDTO ==null)) {
                    return new ResponseUtil(200, "Driver Login Successfully....", driverDTO);
                }else {
                    return new ResponseUtil(200, "Incorrect username and password", null);
                }
            } else {
                return new ResponseUtil(200, "Admin Login Successfully....", adminDTO);
            }
        } else {
            return new ResponseUtil(200, "Customer Login Successfully....", customerDTO);
        }
    }

}
