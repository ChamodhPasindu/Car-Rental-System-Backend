package lk.ijse.spring.service;

import lk.ijse.spring.dto.AdminDTO;
import lk.ijse.spring.entity.Admin;
import lk.ijse.spring.entity.Customer;

public interface AdminService {

    AdminDTO checkAdminLogIn(String id, String password);

}
