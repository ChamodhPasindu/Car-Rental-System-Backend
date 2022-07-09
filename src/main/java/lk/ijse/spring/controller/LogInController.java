package lk.ijse.spring.controller;

import lk.ijse.spring.dto.AdminDTO;
import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.service.AdminService;
import lk.ijse.spring.service.CustomerService;
import lk.ijse.spring.util.ResponseUtil;
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

    @GetMapping(params = {"user_name", "password"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil checkUserNameAndPassword(@RequestParam String user_name, @RequestParam String password) {

        CustomerDTO customerDTO = customerService.checkCustomerLogIn(user_name, password);
        if (customerDTO == null) {
            AdminDTO adminDTO = adminService.checkAdminLogIn(user_name, password);
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
